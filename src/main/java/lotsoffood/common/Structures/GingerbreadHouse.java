package lotsoffood.common.Structures;

import com.mojang.serialization.MapCodec;
import lotsoffood.LotsfoodMod;
import lotsoffood.common.registry.ModStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.Optional;

public class GingerbreadHouse extends Structure {
    public static final MapCodec<GingerbreadHouse> CODEC = simpleCodec(GingerbreadHouse::new);
    private static final ResourceLocation TEMPLATE_LOCATION = ResourceLocation.fromNamespaceAndPath(LotsfoodMod.MODID, "gingerbreadhouse");

    private static boolean warnedEmpty;

    public GingerbreadHouse(StructureSettings settings) {
        super(settings);
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        StructureTemplate template = context.structureTemplateManager().getOrCreate(TEMPLATE_LOCATION);
        Vec3i size = template.getSize();

        if (size.equals(Vec3i.ZERO)) {
            if (!warnedEmpty) {
                warnedEmpty = true;
                LotsfoodMod.LOGGER.warn("GingerbreadHouse template '{}' is empty (size 0x0x0). "
                    + "Place a valid .nbt file at data/lotsoffood/structure/gingerbreadhouse.nbt",
                    TEMPLATE_LOCATION);
            }
            // Don't return empty here — let it generate so /locate can find it.
            // The piece's postProcess will skip placing blocks since the template is empty.
        }

        ChunkPos chunkPos = context.chunkPos();
        int x = chunkPos.getMiddleBlockX();
        int z = chunkPos.getMiddleBlockZ();
        int y = context.chunkGenerator().getFirstOccupiedHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());

        if (y < context.chunkGenerator().getSeaLevel()) {
            return Optional.empty();
        }

        BlockPos cornerPos = new BlockPos(x - size.getX() / 2, y, z - size.getZ() / 2);

        return Optional.of(new GenerationStub(cornerPos, builder ->
            builder.addPiece(new GingerbreadHousePiece(context.structureTemplateManager(), TEMPLATE_LOCATION, cornerPos, size))
        ));
    }

    @Override
    public StructureType<?> type() {
        return ModStructures.GINGERBREAD_HOUSE.get();
    }
}
