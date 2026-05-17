package lotsoffood.common.Structures;

import lotsoffood.LotsfoodMod;
import lotsoffood.common.registry.ModStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GingerbreadHousePiece extends StructurePiece {
    private static final ResourceLocation TEMPLATE_LOCATION = ResourceLocation.fromNamespaceAndPath(LotsfoodMod.MODID, "gingerbreadhouse");
    private static final TagKey<Item> FOOD_TAG = TagKey.create(Registries.ITEM,
        ResourceLocation.fromNamespaceAndPath(LotsfoodMod.MODID, "gingerbread_house_food"));

    private final StructureTemplateManager structureTemplateManager;
    private final ResourceLocation templateLocation;

    public GingerbreadHousePiece(StructureTemplateManager structureTemplateManager, ResourceLocation templateLocation, BlockPos pos, Vec3i size) {
        super(ModStructures.GINGERBREAD_HOUSE_PIECE.get(), 0,
            new BoundingBox(pos.getX(), pos.getY(), pos.getZ(),
                          pos.getX() + size.getX(), pos.getY() + size.getY(), pos.getZ() + size.getZ()));
        this.structureTemplateManager = structureTemplateManager;
        this.templateLocation = templateLocation;
    }

    public GingerbreadHousePiece(StructureTemplateManager structureTemplateManager, CompoundTag tag) {
        super(ModStructures.GINGERBREAD_HOUSE_PIECE.get(), tag);
        this.structureTemplateManager = structureTemplateManager;
        this.templateLocation = ResourceLocation.parse(tag.getString("Template"));
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {
        tag.putString("Template", this.templateLocation.toString());
    }

    @Override
    public void postProcess(WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator,
                           RandomSource random, BoundingBox box, ChunkPos chunkPos, BlockPos pos) {
        StructureTemplate template = this.structureTemplateManager.getOrCreate(this.templateLocation);
        if (template.getSize().equals(Vec3i.ZERO)) {
            return;
        }
        BlockPos cornerPos = new BlockPos(this.boundingBox.minX(), this.boundingBox.minY(), this.boundingBox.minZ());
        StructurePlaceSettings settings = new StructurePlaceSettings()
            .setRotation(Rotation.NONE)
            .setRandom(random)
            .setBoundingBox(box)
            .setKnownShape(true);
        template.placeInWorld(level, cornerPos, cornerPos, settings, random, 18);

        Optional<List<Item>> foodItems = resolveFoodItems(level);
        if (foodItems.isEmpty()) {
            return;
        }
        List<Item> items = foodItems.get();

        for (BlockPos checkPos : BlockPos.betweenClosed(
            box.minX(), box.minY(), box.minZ(),
            box.maxX(), box.maxY(), box.maxZ()
        )) {
            if (level.getBlockEntity(checkPos) instanceof RandomizableContainerBlockEntity container) {
                container.setLootTable(null);
                for (int i = 0; i < 5; i++) {
                    Item item = items.get(random.nextInt(items.size()));
                    int count = random.nextIntBetweenInclusive(1, 3);
                    container.setItem(random.nextInt(container.getContainerSize()), new ItemStack(item, count));
                }
            }
        }
    }

    private static Optional<List<Item>> resolveFoodItems(WorldGenLevel level) {
        return level.registryAccess().lookup(Registries.ITEM)
            .flatMap(lookup -> lookup.get(FOOD_TAG))
            .map(holders -> {
                List<Item> items = new ArrayList<>();
                for (Holder<Item> holder : holders) {
                    items.add(holder.value());
                }
                return items;
            });
    }
}
