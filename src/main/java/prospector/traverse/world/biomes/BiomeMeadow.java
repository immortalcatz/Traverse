package prospector.traverse.world.biomes;

import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import prospector.traverse.world.ITreeConstants;

import java.util.Random;

public class BiomeMeadow extends Biome implements ITreeConstants {

    public static BiomeProperties properties = new BiomeProperties("Meadow");

    static {
        properties.setTemperature(0.8F);
        properties.setRainfall(0.7F);
        properties.setBaseHeight(0.1F);
        properties.setHeightVariation(0F);
    }

    public BiomeMeadow() {
        super(properties);
        decorator.treesPerChunk = -999;
        decorator.extraTreeChance = -999;
        decorator.flowersPerChunk = 15;
        decorator.grassPerChunk = 15;
        decorator.reedsPerChunk = 2;

        spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 4));
        spawnableCreatureList.add(new SpawnListEntry(EntityDonkey.class, 1, 1, 2));

        this.flowers.clear();
        for (BlockFlower.EnumFlowerType type : BlockFlower.EnumFlowerType.values()) {
            if (type.getBlockType() == BlockFlower.EnumFlowerColor.YELLOW) continue;
            if (type == BlockFlower.EnumFlowerType.BLUE_ORCHID) type = BlockFlower.EnumFlowerType.POPPY;
            addFlower(net.minecraft.init.Blocks.RED_FLOWER.getDefaultState().withProperty(net.minecraft.init.Blocks.RED_FLOWER.getTypeProperty(), type), 10);
        }
    }

    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
        double d0 = GRASS_COLOR_NOISE.getValue((double) pos.getX() / 200.0D, (double) pos.getZ() / 200.0D);

        if (d0 < -0.8D) {
            int j = rand.nextInt(4);

            switch (j) {
                case 0:
                    return BlockFlower.EnumFlowerType.ORANGE_TULIP;
                case 1:
                    return BlockFlower.EnumFlowerType.RED_TULIP;
                case 2:
                    return BlockFlower.EnumFlowerType.PINK_TULIP;
                case 3:
                default:
                    return BlockFlower.EnumFlowerType.WHITE_TULIP;
            }
        } else if (rand.nextInt(3) > 0) {
            int i = rand.nextInt(3);
            return i == 0 ? BlockFlower.EnumFlowerType.POPPY : (i == 1 ? BlockFlower.EnumFlowerType.HOUSTONIA : BlockFlower.EnumFlowerType.OXEYE_DAISY);
        } else {
            return BlockFlower.EnumFlowerType.DANDELION;
        }
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return 0xFF4DD838;
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return 0xFF4DD838;
    }
}
