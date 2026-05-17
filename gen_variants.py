import json, os

base = os.path.join(os.path.dirname(os.path.abspath(__file__)), "src", "main", "resources", "assets", "lotsoffood")
blocks = ["bricksucre", "caram", "chocobloc"]

def write(path, obj):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w') as f:
        json.dump(obj, f, indent=2)
        f.write('\n')
    print(f"  Created: {os.path.relpath(path, base)}")

for b in blocks:
    tex = f"lotsoffood:block/{b}"
    print(f"Generating variants for: {b}")

    # --- SLAB ---
    write(f"{base}/blockstates/{b}_slab.json", {
        "variants": {
            "type=bottom": {"model": f"lotsoffood:block/{b}_slab"},
            "type=top":    {"model": f"lotsoffood:block/{b}_slab_top"},
            "type=double": {"model": f"lotsoffood:block/{b}"}
        }
    })
    for m in ["slab", "slab_top"]:
        parent = "minecraft:block/slab" if m == "slab" else "minecraft:block/slab_top"
        write(f"{base}/models/block/{b}_{m}.json", {
            "parent": parent,
            "textures": {"bottom": tex, "side": tex, "top": tex}
        })

    # --- STAIRS ---
    variants = {}
    for facing, fy in [("east",0), ("north",270), ("south",90), ("west",180)]:
        for half, hx in [("bottom",0), ("top",180)]:
            for shape, smodel, sy in [
                ("inner_left",  f"{b}_stairs_inner", 270),
                ("inner_right", f"{b}_stairs_inner", 0),
                ("outer_left",  f"{b}_stairs_outer", 270),
                ("outer_right", f"{b}_stairs_outer", 0),
                ("straight",    f"{b}_stairs",       0),
            ]:
                rot = {}
                if hx != 0: rot["x"] = hx
                if (fy + sy) % 360 != 0: rot["y"] = (fy + sy) % 360
                if hx != 0 or (fy + sy) % 360 != 0: rot["uvlock"] = True
                key = f"facing={facing},half={half},shape={shape}"
                variants[key] = {"model": f"lotsoffood:block/{smodel}", **rot}
    write(f"{base}/blockstates/{b}_stairs.json", {"variants": variants})

    for m, parent in [("stairs", "stairs"), ("stairs_inner", "inner_stairs"), ("stairs_outer", "outer_stairs")]:
        write(f"{base}/models/block/{b}_{m}.json", {
            "parent": f"minecraft:block/{parent}",
            "textures": {"bottom": tex, "side": tex, "top": tex}
        })

    # --- FENCE ---
    write(f"{base}/blockstates/{b}_fence.json", {
        "multipart": [
            {"apply": {"model": f"lotsoffood:block/{b}_fence_post"}},
            {"when": {"north": "true"}, "apply": {"model": f"lotsoffood:block/{b}_fence_side", "uvlock": True}},
            {"when": {"east": "true"},  "apply": {"model": f"lotsoffood:block/{b}_fence_side", "y": 90, "uvlock": True}},
            {"when": {"south": "true"}, "apply": {"model": f"lotsoffood:block/{b}_fence_side", "y": 180, "uvlock": True}},
            {"when": {"west": "true"},  "apply": {"model": f"lotsoffood:block/{b}_fence_side", "y": 270, "uvlock": True}}
        ]
    })
    for m, parent in [("fence_post", "fence_post"), ("fence_side", "fence_side"), ("fence_inventory", "fence_inventory")]:
        write(f"{base}/models/block/{b}_{m}.json", {
            "parent": f"minecraft:block/{parent}",
            "textures": {"texture": tex}
        })

    # --- TRAPDOOR ---
    mapping = {
        "east,bottom,false":  (f"{b}_trapdoor_bottom", 0, 90),
        "east,bottom,true":   (f"{b}_trapdoor_open", 0, 90),
        "east,top,false":     (f"{b}_trapdoor_top", 0, 90),
        "east,top,true":      (f"{b}_trapdoor_open", 180, 270),
        "north,bottom,false": (f"{b}_trapdoor_bottom", 0, 0),
        "north,bottom,true":  (f"{b}_trapdoor_open", 0, 0),
        "north,top,false":    (f"{b}_trapdoor_top", 0, 0),
        "north,top,true":     (f"{b}_trapdoor_open", 180, 180),
        "south,bottom,false": (f"{b}_trapdoor_bottom", 0, 180),
        "south,bottom,true":  (f"{b}_trapdoor_open", 0, 180),
        "south,top,false":    (f"{b}_trapdoor_top", 0, 180),
        "south,top,true":     (f"{b}_trapdoor_open", 180, 0),
        "west,bottom,false":  (f"{b}_trapdoor_bottom", 0, 270),
        "west,bottom,true":   (f"{b}_trapdoor_open", 0, 270),
        "west,top,false":     (f"{b}_trapdoor_top", 0, 270),
        "west,top,true":      (f"{b}_trapdoor_open", 180, 90),
    }
    td_variants = {}
    for key, (model, rx, ry) in mapping.items():
        facing, half, open_s = key.split(",")
        entry = {"model": f"lotsoffood:block/{model}"}
        if rx: entry["x"] = rx
        if ry: entry["y"] = ry
        td_variants[f"facing={facing},half={half},open={open_s}"] = entry
    write(f"{base}/blockstates/{b}_trapdoor.json", {"variants": td_variants})

    for m, parent in [("trapdoor_bottom", "template_orientable_trapdoor_bottom"),
                       ("trapdoor_top", "template_orientable_trapdoor_top"),
                       ("trapdoor_open", "template_orientable_trapdoor_open")]:
        write(f"{base}/models/block/{b}_{m}.json", {
            "parent": f"minecraft:block/{parent}",
            "textures": {"texture": tex}
        })

    # --- ITEM MODELS ---
    for suffix in ["slab", "stairs", "fence", "trapdoor"]:
        write(f"{base}/models/item/{b}_{suffix}.json", {
            "parent": f"lotsoffood:block/{b}_{suffix}"
        })
    # Fence item uses inventory model instead
    write(f"{base}/models/item/{b}_fence.json", {
        "parent": f"lotsoffood:block/{b}_fence_inventory"
    })

print("\nAll variant JSON files generated successfully!")
print(f"Total: {len(blocks)} base blocks x (slab, stairs, fence, trapdoor) = {len(blocks)*4} variants")
