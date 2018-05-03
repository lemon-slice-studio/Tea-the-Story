package roito.teastory.item;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsLoader;

public class ItemTeaDrink extends ItemFood
{
	public ItemTeaDrink(String name)
	{
		super(1, false);
		this.setCreativeTab(CreativeTabsLoader.tabDrink);
		this.setAlwaysEdible();
		this.setMaxStackSize(4);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(TeaStory.MODID, name));
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
		if (this.isInCreativeTab(tab))
		{
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			tooltip.add(TextFormatting.WHITE + I18n.translateToLocal("teastory.tooltip.cup"));
		}
		else
			tooltip.add(TextFormatting.ITALIC + I18n.translateToLocal("teastory.tooltip.shiftfordetail"));
	}


	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		String name;
		switch(stack.getItemDamage())
		{
		case 2:
			name = "stone";
			break;
		case 3:
			name = "glass";
			break;
		case 4:
			name = "porcelain";
			break;
		case 5:
			name = "zisha";
			break;
		default:
			name = "wood";
		}
		return super.getUnlocalizedName() + "." + name;
	}
	
	@Nullable
	public Block getBlock(int meta)
	{
		return null;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (player.isSneaking())
		{
			Block drinkblock = getBlock(player.getHeldItem(hand).getItemDamage());
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (!block.isReplaceable(worldIn, pos))
			{
				pos = pos.offset(facing);
			}

			if (player.getHeldItem(hand).getCount() != 0 && player.canPlayerEdit(pos, facing, player.getHeldItem(hand)) && worldIn.mayPlace(drinkblock, pos, false, facing, (Entity)null))
			{
				int i = this.getMetadata(player.getHeldItem(hand).getMetadata());
				IBlockState iblockstate1 = drinkblock.getDefaultState();

				if (placeBlockAt(player.getHeldItem(hand), player, worldIn, pos, facing, hitX, hitY, hitZ, iblockstate1))
				{
					SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, player);
					worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
					player.getHeldItem(hand).shrink(1);
				}

				return EnumActionResult.SUCCESS;
			}
			else
			{
				return EnumActionResult.FAIL;
			}
		}
		else
		{
			return EnumActionResult.PASS;
		}
	}
	
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
	{
		if (!world.setBlockState(pos, newState, 3)) return false;

		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == getBlock(stack.getItemDamage()))
		{
			ItemBlock.setTileEntityNBT(world, player, pos, stack);
			getBlock(stack.getItemDamage()).onBlockPlacedBy(world, pos, state, player, stack);
		}

		return true;
	}

	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	{
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));
        }
        
		if (stack.getCount() > 1)
		{
			ItemHandlerHelper.giveItemToPlayer((EntityPlayer) entityLiving, new ItemStack(ItemLoader.cup, 1, stack.getItemDamage()));
			stack.shrink(1);
			return stack;
		}
		return new ItemStack(ItemLoader.cup, 1, stack.getItemDamage());
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemStackIn)
	{
		return EnumAction.DRINK;
	}
}