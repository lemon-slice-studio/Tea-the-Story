package roito.teastory.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsLoader;
import roito.teastory.helper.EntironmentHelper;
import roito.teastory.item.ItemLoader;

public class Barrel extends Block
{
	public Barrel()
	{
		super(Material.WOOD);
		this.setHardness(0.5F);
		this.setSoundType(SoundType.WOOD);
		this.setTickRandomly(true);
		this.setUnlocalizedName("barrel");
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, "barrel"));
		this.setDefaultState(this.blockState.getBaseState().withProperty(STEP, 0));
		this.setCreativeTab(CreativeTabsLoader.tabTeaStory);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public ArrayList getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune)
	{
		ArrayList drops = new ArrayList();
		drops.add(new ItemStack(BlockLoader.barrel, 1));
		int meta = BlockLoader.barrel.getMetaFromState(blockstate);
		if ((meta >= 1) && (meta <= 7))
		{
			drops.add(new ItemStack(ItemLoader.half_dried_tea, 8));
		} 
		else if (meta == 8)
		{
			drops.add(new ItemStack(ItemLoader.black_tea_leaf, 8));
		}
		return drops;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		int meta = getMetaFromState(worldIn.getBlockState(pos));
		if ((meta >= 2) && (meta <= 7))
		{
			float f = EntironmentHelper.getFermentationChance(worldIn, pos, false);
			if (f == 0.0F)
			{
				return;
			} 
			else if (rand.nextInt((int) (25.0F / f) + 1) == 0)
			{
				worldIn.setBlockState(pos, BlockLoader.barrel.getStateFromMeta(meta + 1));
			}
		}
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { STEP });
	}

	@Override
	public IBlockState getStateFromMeta(int step)
	{
		return this.getDefaultState().withProperty(this.getStepProperty(), Integer.valueOf(step));
	}

	protected PropertyInteger getStepProperty()
	{
		return STEP;
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(this.getStepProperty()).intValue();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
		return new ItemStack(this);
    }

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		int step = getMetaFromState(worldIn.getBlockState(pos));
		if (worldIn.isRemote)
		{
			if (step == 0)
			{
				if ((playerIn.getHeldItem(hand).isEmpty()) || (playerIn.getHeldItem(hand).getItem() != ItemLoader.half_dried_tea)
						&& (Block.getBlockFromItem(playerIn.getHeldItem(hand).getItem()) != BlockLoader.barrel))
				{
					playerIn.sendMessage(new TextComponentTranslation("teastory.message.barrel.tips"));
				} 
				else if ((!playerIn.getHeldItem(hand).isEmpty()) && (playerIn.getHeldItem(hand).getItem() == ItemLoader.half_dried_tea && playerIn.getHeldItem(hand).getCount() < 8))
				{
					playerIn.sendMessage(new TextComponentTranslation("teastory.message.barrel.notenough"));
				}
				else if ((!playerIn.getHeldItem(hand).isEmpty()) && (playerIn.getHeldItem(hand).getItem() == ItemLoader.half_dried_tea && playerIn.getHeldItem(hand).getCount() >= 8))
				{
					playerIn.sendMessage(new TextComponentTranslation("teastory.message.barrel.knead"));
				}
				return true;
			} 
			else if (step == 1)
			{
				
			}
			else if ((step >= 2) && (step <= 4))
			{
				if (!playerIn.isSneaking())
				{
					playerIn.sendMessage(new TextComponentTranslation("teastory.message.barrel.fermentation.1"));
				}
				return true;
			} 
			else if ((step >= 5) && (step <= 7))
			{
				if (!(playerIn.isSneaking()))
				{
					playerIn.sendMessage(new TextComponentTranslation("teastory.message.barrel.fermentation.2"));
				}
				return true;
			}
		} 
		else
		{
			if (step == 0)
			{
				if (!playerIn.getHeldItem(hand).isEmpty() && playerIn.getHeldItem(hand).getItem() == ItemLoader.half_dried_tea && playerIn.getHeldItem(hand).getCount() >= 8)
				{
					worldIn.setBlockState(pos, this.getStateFromMeta(1));
					if (!playerIn.capabilities.isCreativeMode)
						playerIn.getHeldItem(hand).shrink(8);
					return true;
				} 
				else
					return false;
			} 
			else if (step == 1)
			{
				if (playerIn.isSneaking())
				{
					worldIn.setBlockState(pos, this.getDefaultState());
					ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.half_dried_tea, 8));
					return true;
				} 
				else
				{
					worldIn.setBlockState(pos, this.getStateFromMeta(2));
					return true;
				}
			} 
			else if ((step >= 2) && (step <= 7))
			{
				if (playerIn.isSneaking())
				{
					worldIn.setBlockState(pos, this.getDefaultState());
					ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.half_dried_tea, 8));
					return true;
				} 
				else
				return false;
			} 
			else if (step == 8)
			{
				worldIn.setBlockState(pos, this.getDefaultState());
				ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(ItemLoader.black_tea_leaf, 8));
				return true;
			}
		}
		return false;
	}

	public static final PropertyInteger STEP = PropertyInteger.create("step", 0, 8);
}