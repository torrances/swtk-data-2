package org.swtk.commons.dict.wiktionary;

import org.swtk.common.dict.dto.wiktionary.Entry;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbA;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbB;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbC;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbD;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbE;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbF;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbG;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbH;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbI;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbJ;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbK;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbL;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbM;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbN;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbO;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbP;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbQ;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbR;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbS;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbT;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbU;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbV;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbW;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbX;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbY;
import org.swtk.commons.dict.wiktionary.controller.WiktionaryDbZ;

public final class WiktionaryDb {

	public static Entry findByName(String name) {
		if (null == name || 0 == name.length()) return null;
		name = name.toLowerCase();
		String key = name.substring(0, 1);

		if ("a".equals(key)) return WiktionaryDbA.findByName(name);
		else if ("b".equals(key)) return WiktionaryDbB.findByName(name);
		else if ("c".equals(key)) return WiktionaryDbC.findByName(name);
		else if ("d".equals(key)) return WiktionaryDbD.findByName(name);
		else if ("e".equals(key)) return WiktionaryDbE.findByName(name);
		else if ("f".equals(key)) return WiktionaryDbF.findByName(name);
		else if ("g".equals(key)) return WiktionaryDbG.findByName(name);
		else if ("h".equals(key)) return WiktionaryDbH.findByName(name);
		else if ("i".equals(key)) return WiktionaryDbI.findByName(name);
		else if ("j".equals(key)) return WiktionaryDbJ.findByName(name);
		else if ("k".equals(key)) return WiktionaryDbK.findByName(name);
		else if ("l".equals(key)) return WiktionaryDbL.findByName(name);
		else if ("m".equals(key)) return WiktionaryDbM.findByName(name);
		else if ("n".equals(key)) return WiktionaryDbN.findByName(name);
		else if ("o".equals(key)) return WiktionaryDbO.findByName(name);
		else if ("p".equals(key)) return WiktionaryDbP.findByName(name);
		else if ("q".equals(key)) return WiktionaryDbQ.findByName(name);
		else if ("r".equals(key)) return WiktionaryDbR.findByName(name);
		else if ("s".equals(key)) return WiktionaryDbS.findByName(name);
		else if ("t".equals(key)) return WiktionaryDbT.findByName(name);
		else if ("u".equals(key)) return WiktionaryDbU.findByName(name);
		else if ("v".equals(key)) return WiktionaryDbV.findByName(name);
		else if ("w".equals(key)) return WiktionaryDbW.findByName(name);
		else if ("x".equals(key)) return WiktionaryDbX.findByName(name);
		else if ("y".equals(key)) return WiktionaryDbY.findByName(name);
		else if ("z".equals(key)) return WiktionaryDbZ.findByName(name);

		return null;
	}
}
