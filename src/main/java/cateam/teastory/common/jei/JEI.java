package cateam.teastory.common.jei;

import cateam.teastory.block.BlockLoader;
import cateam.teastory.client.gui.GuiContainerTeaStove;
import cateam.teastory.client.gui.GuiContainerTeaTable;
import cateam.teastory.item.ItemLoader;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEI implements IModPlugin
{

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry)
	{
		
	}

	@Override
	public void registerIngredients(IModIngredientRegistration registry)
	{
		
	}

	@Override
	public void register(IModRegistry registry)
	{
		IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.blacktea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.blacktea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.blacktea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.blacktea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.blacktea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.greentea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.greentea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.greentea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.greentea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.greentea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.matchadrink_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.matchadrink_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.matchadrink_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.matchadrink_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.matchadrink_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.milktea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.milktea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.milktea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.milktea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.milktea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lemontea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lemontea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lemontea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lemontea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lemontea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.yellowtea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.yellowtea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.yellowtea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.yellowtea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.yellowtea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.whitetea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.whitetea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.whitetea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.whitetea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.whitetea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.oolongtea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.oolongtea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.oolongtea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.oolongtea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.oolongtea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.puertea_glass_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.puertea_porcelain_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.puertea_stone_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.puertea_wood_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.puertea_zisha_cup));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lit_cooking_pan));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lit_tea_stove));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lit_tea_drying_pan));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.black_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.green_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.matcha_drink_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.milk_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.lemon_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.yellow_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.white_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.oolong_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.puer_tea_zisha_kettle));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.field));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.rice_plant));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.rice_seedling));
		blacklist.addIngredientToBlacklist(new ItemStack(BlockLoader.teaplant));
		blacklist.addIngredientToBlacklist(new ItemStack(ItemLoader.cup, 1, 1));
		
		registry.addRecipeHandlers(new HandlerTeaStove());
		registry.addRecipeCategories(new CategoryTeaStove(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategoryCraftingItem(new ItemStack(BlockLoader.tea_stove), "teastory.teastove");
		registry.addRecipes(RecipeTeaStove.getWrappedRecipeList());
		registry.addRecipeClickArea(GuiContainerTeaStove.class, 78, 38, 24, 17, "teastory.teastove");
		
		registry.addRecipeHandlers(new HandlerTeaTable());
		registry.addRecipeCategories(new CategoryTeaTable(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategoryCraftingItem(new ItemStack(BlockLoader.tea_table), "teastory.teatable");
		registry.addRecipes(RecipeTeaTable.getWrappedRecipeList());
		registry.addRecipeClickArea(GuiContainerTeaTable.class, 85, 33, 24, 17, "teastory.teatable");
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
	{
		
	}
}
