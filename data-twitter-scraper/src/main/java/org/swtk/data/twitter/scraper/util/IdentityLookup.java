package org.swtk.data.twitter.scraper.util;

import java.util.Map;
import java.util.TreeMap;

import org.swtk.data.twitter.core.dto.type.AgeRange;
import org.swtk.data.twitter.core.dto.type.Gender;

import com.trimc.blogger.commons.utils.string.StringUtils;

public class IdentityLookup {

	public static Map<String, AgeRange>	ageRangeMap	= new TreeMap<String, AgeRange>();

	public static Map<String, Gender>	genderMap	= new TreeMap<String, Gender>();

	static {
		put("_anisafarah", AgeRange.R_12_17, Gender.FEMALE);
		put("canelatrigueros", AgeRange.R_12_17, Gender.FEMALE);
		put("ArianaGrande", AgeRange.R_18_24, Gender.FEMALE);
		put("selenagomez", AgeRange.R_18_24, Gender.FEMALE);
		put("NettaJxn", AgeRange.R_18_24, Gender.FEMALE);
		put("taylorswift13", AgeRange.R_25_34, Gender.FEMALE);
		put("NICKIMINAJ", AgeRange.R_25_34, Gender.FEMALE);
		put("fayfeeney", AgeRange.R_55_64, Gender.FEMALE);
		put("SawyerFrdrx", AgeRange.R_12_17, Gender.FEMALE);
		put("MissScarletTana", AgeRange.R_18_24, Gender.FEMALE);
		put("dentistmel", AgeRange.R_35_44, Gender.FEMALE);
		put("CarlyPage_", AgeRange.R_55_64, Gender.FEMALE);

		put("realdavidmazouz", AgeRange.R_12_17, Gender.MALE);
		put("KarlosLabajo", AgeRange.R_12_17, Gender.MALE);
		put("destiny_maulden", AgeRange.R_12_17, Gender.FEMALE);
		put("LordSCruz", AgeRange.R_12_17, Gender.MALE);
		put("kaaayleeeeeeeee", AgeRange.R_12_17, Gender.FEMALE);
		put("H_Gorsching", AgeRange.R_12_17, Gender.FEMALE);

		put("Susiii_29", AgeRange.R_18_24, Gender.FEMALE);
		put("nichol3l33", AgeRange.R_25_34, Gender.FEMALE);
		put("smile_a_thon", AgeRange.R_25_34, Gender.FEMALE);
		put("RoxyCoreActive", AgeRange.R_25_34, Gender.FEMALE);
		put("bassem_masri", AgeRange.R_25_34, Gender.MALE);

		put("JulSosee", AgeRange.R_35_44, Gender.FEMALE);
		put("Seb_silverfox", AgeRange.R_35_44, Gender.MALE);
		put("denisha_jones", AgeRange.R_35_44, Gender.FEMALE);
		put("lemonsmama", AgeRange.R_35_44, Gender.FEMALE);
		put("ScottFordTVGuy", AgeRange.R_35_44, Gender.FEMALE);
		put("CurvyGirlz", AgeRange.R_35_44, Gender.FEMALE);
		put("HeatherWolfpack", AgeRange.R_35_44, Gender.FEMALE);
		put("kevpluck", AgeRange.R_35_44, Gender.MALE);
		put("davidgagnonart", AgeRange.R_35_44, Gender.MALE);
		put("Regine357", AgeRange.R_35_44, Gender.FEMALE);
		put("LElGHBRON", AgeRange.R_35_44, Gender.FEMALE);
		put("rkargas", AgeRange.R_35_44, Gender.FEMALE);
		put("woogurl", AgeRange.R_35_44, Gender.FEMALE);

		put("liltreeimp", AgeRange.R_25_34, Gender.FEMALE);
		put("joanneeatswell", AgeRange.R_25_34, Gender.FEMALE);
		put("Glaakso", AgeRange.R_25_34, Gender.MALE);
		put("ryanooo", AgeRange.R_25_34, Gender.MALE);
		put("jzakskorn", AgeRange.R_25_34, Gender.FEMALE);
		put("AnnisaAdjani", AgeRange.R_25_34, Gender.FEMALE);

		put("TimothyODonnel2", AgeRange.R_35_44, Gender.MALE);
		put("TheBigGuyWJCL", AgeRange.R_35_44, Gender.MALE);
		put("BStretton96", AgeRange.R_35_44, Gender.MALE);
		put("vicsawyers", AgeRange.R_45_54, Gender.MALE);
		put("hosueofcards", AgeRange.R_35_44, Gender.FEMALE);
		put("Matt_Striker_", AgeRange.R_35_44, Gender.MALE);

		put("jamieblue5", AgeRange.R_18_24, Gender.MALE);
		put("beamandaniel", AgeRange.R_45_54, Gender.MALE);
		put("ArsenalCyprus02", AgeRange.R_45_54, Gender.MALE);
		put("pjwiedner", AgeRange.R_45_54, Gender.MALE);
		put("JensenClan88", AgeRange.R_45_54, Gender.MALE);
		put("GODH8SFATGIRLS", AgeRange.R_45_54, Gender.MALE);
		put("getbentsaggy", AgeRange.R_25_34, Gender.MALE);
		put("MagnusMoan", AgeRange.R_25_34, Gender.MALE);
		put("BarbiturateCat", AgeRange.R_25_34, Gender.FEMALE);
		put("JoshHanson18", AgeRange.R_25_34, Gender.MALE);
		put("Poutymcgee", AgeRange.R_25_34, Gender.FEMALE);

		put("dougiefischer", AgeRange.R_45_54, Gender.MALE);
		put("jacobsartorius", AgeRange.R_12_17, Gender.MALE);
		put("floppycheese96", AgeRange.R_18_24, Gender.MALE);
		put("CrawfordCollins", AgeRange.R_18_24, Gender.MALE);
		put("Harry_Styles", AgeRange.R_18_24, Gender.MALE);
		put("Harvey", AgeRange.R_12_17, Gender.MALE);
		put("camerondallas", AgeRange.R_18_24, Gender.MALE);
		put("Nashgrier", AgeRange.R_18_24, Gender.MALE);
		put("justinbieber", AgeRange.R_18_24, Gender.MALE);
		put("Tylernizzle", AgeRange.R_18_24, Gender.MALE);
		put("danwardcomic", AgeRange.R_35_44, Gender.MALE);
		put("simmonssteve", AgeRange.R_55_64, Gender.MALE);
		put("CarterWheat", AgeRange.R_55_64, Gender.MALE);

		put("AlinaOrgana", AgeRange.R_12_17, Gender.FEMALE);
		put("GerryOrz", AgeRange.R_12_17, Gender.MALE);
		put("micheladams78", AgeRange.R_12_17, Gender.MALE);
		put("ItsRair", AgeRange.R_12_17, Gender.MALE);
		put("meghan_graham1", AgeRange.R_18_24, Gender.FEMALE);
		put("chrisbrownxsoon", AgeRange.R_12_17, Gender.FEMALE);
		put("la_rubiaxo", AgeRange.R_18_24, Gender.FEMALE);
		put("kegelmonster", AgeRange.R_18_24, Gender.FEMALE);
		put("trollmarie01", AgeRange.R_12_17, Gender.FEMALE);
		put("jordannn_13", AgeRange.R_12_17, Gender.FEMALE);
		put("sivanscat", AgeRange.R_12_17, Gender.MALE);
		put("madeline_sand", AgeRange.R_12_17, Gender.FEMALE);
		put("cassyjacobsen", AgeRange.R_12_17, Gender.FEMALE);
		put("Karenzelaya15", AgeRange.R_18_24, Gender.FEMALE);
		put("Pr1de_Le6eNd", AgeRange.R_12_17, Gender.MALE);
		put("BigHeadCedd", AgeRange.R_25_34, Gender.MALE);
		put("jglxn", AgeRange.R_12_17, Gender.FEMALE);
		put("kristen_sarver", AgeRange.R_18_24, Gender.FEMALE);
		put("mhas_14", AgeRange.R_18_24, Gender.FEMALE);
		put("IzzieShedd", AgeRange.R_12_17, Gender.FEMALE);
		put("SabineAlcantara", AgeRange.R_12_17, Gender.FEMALE);
		put("Virgin_Thoughtz", AgeRange.R_18_24, Gender.FEMALE);
		put("wildfire_Kerry", AgeRange.R_12_17, Gender.FEMALE);
		put("MSteinum", AgeRange.R_12_17, Gender.FEMALE);
		put("AlexMcNair2", AgeRange.R_12_17, Gender.MALE);
		put("josiefell3", AgeRange.R_12_17, Gender.FEMALE);
		put("wondersuggs", AgeRange.R_12_17, Gender.FEMALE);
		put("_vanabana", AgeRange.R_12_17, Gender.FEMALE);
		put("alaicaprilas", AgeRange.R_12_17, Gender.FEMALE);
		put("_Marisaa_14", AgeRange.R_18_24, Gender.FEMALE);
		put("haileycecelia", AgeRange.R_12_17, Gender.FEMALE);
		put("shuwenlovekpop", AgeRange.R_12_17, Gender.FEMALE);
		put("Jhead_13", AgeRange.R_25_34, Gender.MALE);
		put("justTonda", AgeRange.R_45_54, Gender.FEMALE);
		put("EmmyLuvs103", AgeRange.R_12_17, Gender.FEMALE);
		put("naleski01", AgeRange.R_12_17, Gender.MALE);
		put("MattyBRapsIG", AgeRange.R_12_17, Gender.MALE);
		put("thefatimakhalid", AgeRange.R_12_17, Gender.FEMALE);
		put("IsaacColeMusic", AgeRange.R_12_17, Gender.MALE);
		put("sandraisntcool", AgeRange.R_12_17, Gender.FEMALE);
		put("resentbieber", AgeRange.R_12_17, Gender.FEMALE);
		put("oliviamlamb1010", AgeRange.R_12_17, Gender.FEMALE);
		put("lhans27", AgeRange.R_18_24, Gender.FEMALE);
		put("radical_katical", AgeRange.R_12_17, Gender.FEMALE);
		put("beccapeeler", AgeRange.R_18_24, Gender.FEMALE);
		put("nencolor", AgeRange.R_12_17, Gender.MALE);
		put("BrianaReneeHerr", AgeRange.R_18_24, Gender.FEMALE);
		put("kyloxdanielle", AgeRange.R_12_17, Gender.FEMALE);
		put("headuuur", AgeRange.R_12_17, Gender.FEMALE);
		put("rightinmyarms", AgeRange.R_12_17, Gender.FEMALE);
		put("smolcurls", AgeRange.R_12_17, Gender.MALE);
		put("hotbeachmom", AgeRange.R_12_17, Gender.FEMALE);
		put("zoellawishes", AgeRange.R_12_17, Gender.FEMALE);
		put("EricMoranFilms", AgeRange.R_12_17, Gender.MALE);
		put("_laurenpaige___", AgeRange.R_12_17, Gender.FEMALE);
		put("MMSingsFanClub", AgeRange.R_12_17, Gender.FEMALE);
		put("skydajohnsss  ", AgeRange.R_12_17, Gender.FEMALE);
		put("paytonntaylor  ", AgeRange.R_12_17, Gender.FEMALE);
		put("xmbxrr  ", AgeRange.R_12_17, Gender.FEMALE);
		put("nessa_nowak", AgeRange.R_12_17, Gender.FEMALE);
		put("ebpumpkin", AgeRange.R_12_17, Gender.FEMALE);
		put("Ellia_Nadhira ", AgeRange.R_12_17, Gender.FEMALE);
		put("drrewbieber_", AgeRange.R_12_17, Gender.FEMALE);
		put("emlyphps ", AgeRange.R_12_17, Gender.FEMALE);
		put("saraahw91", AgeRange.R_18_24, Gender.FEMALE);
		put("Odvssy", AgeRange.R_12_17, Gender.MALE);
		put("PRlNCESSKlNK ", AgeRange.R_12_17, Gender.FEMALE);
		put("brookeyoungg", AgeRange.R_12_17, Gender.FEMALE);
		put("RBewersdorf ", AgeRange.R_12_17, Gender.FEMALE);
		put("Grace14O ", AgeRange.R_12_17, Gender.FEMALE);
		put("Brucec__ ", AgeRange.R_12_17, Gender.MALE);
		put("CarolLynnSteven  ", AgeRange.R_12_17, Gender.FEMALE);
		put("vintageandy_ ", AgeRange.R_12_17, Gender.MALE);
		put("AlliSUN_Mabley", AgeRange.R_12_17, Gender.FEMALE);
		put("crz_khaos", AgeRange.R_12_17, Gender.MALE);
		put("brahmav", AgeRange.R_55_64, Gender.FEMALE);
		put("DIG_FrostiaK", AgeRange.R_12_17, Gender.MALE);
		put("EmDogMILionaire 1h", AgeRange.R_12_17, Gender.FEMALE);
		put("brenna_macleod 6h", AgeRange.R_12_17, Gender.FEMALE);
		put("linddsaayye  ", AgeRange.R_12_17, Gender.FEMALE);
		put("DevMcCallister", AgeRange.R_12_17, Gender.FEMALE);
		put("fxckboiiii", AgeRange.R_12_17, Gender.MALE);
		put("rnagnality", AgeRange.R_12_17, Gender.FEMALE);
		put("sarahk523 ", AgeRange.R_12_17, Gender.FEMALE);
		put("earthisquaking ", AgeRange.R_12_17, Gender.FEMALE);
		put("rachelove_9 ", AgeRange.R_12_17, Gender.FEMALE);
		put("VanessaMcniel ", AgeRange.R_12_17, Gender.FEMALE);
		put("sage_mcadams", AgeRange.R_12_17, Gender.FEMALE);
		put("_Jasmania_ ", AgeRange.R_12_17, Gender.FEMALE);
		put("LeleGemperle ", AgeRange.R_12_17, Gender.FEMALE);
		put("Brielle_Roth ", AgeRange.R_12_17, Gender.FEMALE);
		put("_kantoteens ", AgeRange.R_12_17, Gender.FEMALE);
		put("MarkMurills ", AgeRange.R_12_17, Gender.MALE);
		put("HushHaley  ", AgeRange.R_12_17, Gender.FEMALE);
		put("Pricee_Meggaann  ", AgeRange.R_12_17, Gender.FEMALE);
		put("katelyneileen1 ", AgeRange.R_12_17, Gender.FEMALE);
	}

	private static String cleanse(String handle) {
		handle = handle.toLowerCase();
		if (handle.startsWith("@")) handle = StringUtils.substringAfter(handle, "@").trim();

		return handle;
	}

	public static AgeRange findAgeRange(String handle) {
		handle = cleanse(handle);
		if (!ageRangeMap.containsKey(handle)) return AgeRange.R_unknown;
		return ageRangeMap.get(handle);
	}

	public static Gender findGender(String handle) {
		handle = cleanse(handle);
		if (!genderMap.containsKey(handle)) return Gender.UNKNOWN;
		return genderMap.get(handle);
	}

	private static void put(String handle, AgeRange ageRange, Gender gender) {
		handle = cleanse(handle);
		ageRangeMap.put(handle.toLowerCase(), ageRange);
		genderMap.put(handle.toLowerCase(), gender);
	}
}
