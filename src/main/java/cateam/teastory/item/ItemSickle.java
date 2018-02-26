package cateam.teastory.item;

import com.google.common.collect.Multimap;

import cateam.teastory.common.AchievementLoader;
import cateam.teastory.common.CreativeTabsLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSickle extends Item
{
	private final float speed;
    protected Item.ToolMaterial theToolMaterial = ToolMaterial.IRON;
    
	protected ItemSickle()
	{
        this.maxStackSize = 1;
        this.setMaxDamage(500);
        this.setCreativeTab(CreativeTabsLoader.tabRice);
        this.speed = theToolMaterial.getDamageVsEntity() + 0.5F;
        this.setUnlocalizedName("sickle");
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(2, attacker);
        return true;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", theToolMaterial.getDamageVsEntity(), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)(this.speed - 4.0F), 0));
        }

        return multimap;
    }
	
	@Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		return harvestCrops(stack, playerIn, worldIn, pos, 0);
    }
    
    public static EnumActionResult harvestCrops(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, int time)
    {
    	if (worldIn.getChunkFromBlockCoords(pos).isLoaded())
    	{
    		Block block = worldIn.getBlockState(pos).getBlock();
    		if (block instanceof BlockCrops)
    		{
    			if(((BlockCrops) block).isMaxAge(worldIn.getBlockState(pos)))
    			{
    				worldIn.destroyBlock(pos, true);
    				playerIn.addStat(AchievementLoader.riceSeeds);
    				if (stack.getItemDamage() < stack.getMaxDamage() && time < 8)
    				{
    					stack.setItemDamage(stack.getItemDamage() + 1);
    					harvestCrops(stack, playerIn, worldIn, pos.east(), time + 1);
    					harvestCrops(stack, playerIn, worldIn, pos.north(), time + 1);
    					harvestCrops(stack, playerIn, worldIn, pos.west(), time + 1);
    					harvestCrops(stack, playerIn, worldIn, pos.south(), time + 1);
    				}
    				else --stack.stackSize;
    				playerIn.addStat(AchievementLoader.sickle);
    				return EnumActionResult.SUCCESS;
    			}
    		}
    	}
    	return EnumActionResult.FAIL;
    }
}
