package lotsoffood.common.registry;

import com.mojang.serialization.MapCodec;
import lotsoffood.LotsfoodMod;
import lotsoffood.common.Structures.GingerbreadHouse;
import lotsoffood.common.Structures.GingerbreadHousePiece;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
        DeferredRegister.create(Registries.STRUCTURE_TYPE, LotsfoodMod.MODID);

    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECES =
        DeferredRegister.create(Registries.STRUCTURE_PIECE, LotsfoodMod.MODID);

    public static final Supplier<StructureType<GingerbreadHouse>> GINGERBREAD_HOUSE =
        STRUCTURE_TYPES.register("gingerbread_house", () -> () -> GingerbreadHouse.CODEC);

    public static final Supplier<StructurePieceType> GINGERBREAD_HOUSE_PIECE =
        STRUCTURE_PIECES.register("gingerbread_house_piece",
            () -> (StructurePieceType.StructureTemplateType) GingerbreadHousePiece::new);
}
