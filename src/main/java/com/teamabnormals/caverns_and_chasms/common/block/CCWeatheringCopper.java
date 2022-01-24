package com.teamabnormals.caverns_and_chasms.common.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.teamabnormals.caverns_and_chasms.core.registry.CCBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Supplier;

public interface CCWeatheringCopper extends WeatheringCopper {
	Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> HashBiMap.create(ImmutableBiMap.<Block, Block>builder().put(CCBlocks.COPPER_BARS.get(), CCBlocks.EXPOSED_COPPER_BARS.get()).put(CCBlocks.EXPOSED_COPPER_BARS.get(), CCBlocks.WEATHERED_COPPER_BARS.get()).put(CCBlocks.WEATHERED_COPPER_BARS.get(), CCBlocks.OXIDIZED_COPPER_BARS.get()).put(CCBlocks.COPPER_BUTTON.get(), CCBlocks.EXPOSED_COPPER_BUTTON.get()).put(CCBlocks.EXPOSED_COPPER_BUTTON.get(), CCBlocks.WEATHERED_COPPER_BUTTON.get()).put(CCBlocks.WEATHERED_COPPER_BUTTON.get(), CCBlocks.OXIDIZED_COPPER_BUTTON.get()).build()));
	Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());

	@Override
	default Optional<BlockState> getNext(BlockState state) {
		return getNext(state.getBlock()).map((nextState) -> nextState.withPropertiesOf(state));
	}

	static Optional<BlockState> getPrevious(BlockState state) {
		return getPrevious(state.getBlock()).map((prevState) -> prevState.withPropertiesOf(state));
	}

	static BlockState getFirst(BlockState state) {
		return getFirst(state.getBlock()).withPropertiesOf(state);
	}

	static Optional<Block> getNext(Block block) {
		return Optional.ofNullable(NEXT_BY_BLOCK.get().get(block));
	}

	static Optional<Block> getPrevious(Block block) {
		return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(block));
	}

	static Block getFirst(Block block) {
		Block firstBlock = block;

		for (Block previousBlock = PREVIOUS_BY_BLOCK.get().get(block); previousBlock != null; previousBlock = PREVIOUS_BY_BLOCK.get().get(previousBlock)) {
			firstBlock = previousBlock;
		}

		return firstBlock;
	}

}
