package roito.teastory.block;

import java.util.ArrayList;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.item.ItemLoader;
import roito.teastory.tileentity.TileEntityTeaDrink;

public class TeaDrinkFull extends TeaDrink implements ITileEntityProvider
{
	public int drink;

	public TeaDrinkFull(float hardness, String name, Material materialIn, SoundType soundType, int drink, int level)
	{
		super(hardness, name, materialIn, soundType, level);
		this.drink = drink;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityTeaDrink();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return getDrink();
	}
	
	public ItemStack getDrink()
	{
		switch (drink)
		{
		case 2:
			return new ItemStack(ItemLoader.matcha_drink, 1, meta);
		case 3:
			return new ItemStack(ItemLoader.black_tea, 1, meta);
		case 4:
			return new ItemStack(ItemLoader.milk_tea, 1, meta);
		case 5:
			return new ItemStack(ItemLoader.lemon_tea, 1, meta);
		case 6:
			return new ItemStack(ItemLoader.yellow_tea, 1, meta);
		case 7:
			return new ItemStack(ItemLoader.white_tea, 1, meta);
		case 8:
			return new ItemStack(ItemLoader.oolong_tea, 1, meta);
		case 9:
			return new ItemStack(ItemLoader.puer_tea, 1, meta);
		}
		return new ItemStack(ItemLoader.green_tea, 1, meta);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (playerIn.isSneaking())
		{
			ItemHandlerHelper.giveItemToPlayer(playerIn, getDrink());
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			worldIn.removeTileEntity(pos);
			return true;
		}
		else
		{
			TileEntity te = worldIn.getTileEntity(pos);
			if(te instanceof TileEntityTeaDrink)
			{
				if(((TileEntityTeaDrink)te).bite(playerIn, worldIn, drink, meta))
				{
					worldIn.removeTileEntity(pos);
				}
			}
			return true;
		}
	}

	@Override
	public ArrayList getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune)
	{
		ArrayList drops = new ArrayList();
		switch (drink)
		{
		case 1:
			drops.add(new ItemStack(ItemLoader.green_tea, 1, meta));
			break;
		case 2:
			drops.add(new ItemStack(ItemLoader.matcha_drink, 1, meta));
			break;
		case 3:
			drops.add(new ItemStack(ItemLoader.black_tea, 1, meta));
			break;
		case 4:
			drops.add(new ItemStack(ItemLoader.milk_tea, 1, meta));
			break;
		case 5:
			drops.add(new ItemStack(ItemLoader.lemon_tea, 1, meta));
			break;
		case 6:
			drops.add(new ItemStack(ItemLoader.yellow_tea, 1, meta));
			break;
		case 7:
			drops.add(new ItemStack(ItemLoader.white_tea, 1, meta));
			break;
		case 8:
			drops.add(new ItemStack(ItemLoader.oolong_tea, 1, meta));
			break;
		case 9:
			drops.add(new ItemStack(ItemLoader.puer_tea, 1, meta));
			break;
		}
		return drops;
	}
}