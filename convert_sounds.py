import json
import os
import shutil

base_dir = "/home/cookie/Documents/hy mods/Hytale-Example-Project/src/main/resources"
old_json_path = os.path.join(base_dir, "assets/unseen/audio/sound_events.json")
old_audio_dir = os.path.join(base_dir, "assets/unseen/audio/sfx")

new_sound_events_dir = os.path.join(base_dir, "Server/Audio/SoundEvents")
new_sounds_dir = os.path.join(base_dir, "Server/Audio/Sounds/NPC/unseen")

os.makedirs(new_sound_events_dir, exist_ok=True)
os.makedirs(new_sounds_dir, exist_ok=True)

with open(old_json_path, 'r') as f:
    events = json.load(f)

for key, data in events.items():
    # Hytale doesn't use the 'category' at the top level in the same way, we just rely on the parent attenuation.
    
    # We will assume each sound key Maps to an .ogg file of the same name.
    # The old mapping had "sounds": ["audio/sfx/horror_scream"]
    # So we use the key or just grab the filename from the list and append .ogg
    
    sound_files = []
    for s in data['sounds']:
        name = os.path.basename(s)
        ogg_name = f"{name}.ogg"
        
        # move original file if it exists
        old_ogg_path = os.path.join(old_audio_dir, ogg_name)
        if os.path.exists(old_ogg_path):
            shutil.copy(old_ogg_path, os.path.join(new_sounds_dir, ogg_name))
            
        sound_files.append(f"Sounds/NPC/unseen/{ogg_name}")

    hytale_json = {
        "Layers": [
            {
                "Files": sound_files
            }
        ],
        "Volume": data.get("volume", 1.0),
        "Parent": data.get("attenuation", "SFX_Attn_Moderate")
    }

    if data.get("pitch", 1.0) != 1.0:
        hytale_json["Layers"][0]["RandomSettings"] = {
            "MinPitch": data["pitch"] - 1.0,
            "MaxPitch": data["pitch"] - 1.0
        }

    new_json_path = os.path.join(new_sound_events_dir, f"{key}.json")
    with open(new_json_path, 'w') as f:
        json.dump(hytale_json, f, indent=2)

print("Conversion and move completed.")
