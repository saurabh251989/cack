package com.sfcc.ttf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GroupFile {

	private List<String> ptuList;

	private List<String> xrdList;
	private List<String> ttfList;

	private List<String> tioList;
	private List<String> fdcList;

	/**
	 * @param ptuList
	 * @param xrdList
	 * @param tioList
	 * @param fdcList
	 * @param ttfList
	 * 
	 */
	public GroupFile(List<String> ptuList, List<String> xrdList, List<String> tioList, List<String> fdcList,
			List<String> ttfList) {
		this.ptuList = ptuList;
		this.xrdList = xrdList;
		this.ttfList = ttfList;
		this.tioList = tioList;
		this.fdcList = fdcList;
	}

	public HashMap<String, List<String>> genereateLaneGroup() {

		HashMap<String, List<String>> hm = new HashMap<String, List<String>>();

		for (Iterator<String> iterator = ptuList.iterator(); iterator.hasNext();) {
			String ptu = (String) iterator.next();

			List<String> lTTF = new ArrayList<String>();
			String xrd_are = ptu.replace(".ptu", "_opsw_are.xrd");
			String xrd_fcp_a = ptu.replace(".ptu", "_opsw_fcp_a.xrd");
			String xrd_fcp_b = ptu.replace(".ptu", "_opsw_fcp_b.xrd");

			String fdc_are = ptu.replace(".ptu", "_opsw_are.fdc");
			String fdc_fcp_a = ptu.replace(".ptu", "_opsw_fcp_a.fdc");
			String fdc_fcp_b = ptu.replace(".ptu", "_opsw_fcp_b.fdc");

			String tio_are = ptu.replace(".ptu", "_opsw_are.tio");
			String tio_fcp_a = ptu.replace(".ptu", "_opsw_fcp_a.tio");
			String tio_fcp_b = ptu.replace(".ptu", "_opsw_fcp_b.tio");

			if (xrdList.contains(xrd_are) || fdcList.contains(fdc_are) || tioList.contains(tio_are)) {
				lTTF.add("opsw_are");
			}

			if (xrdList.contains(xrd_fcp_a) || fdcList.contains(fdc_fcp_a) || tioList.contains(tio_fcp_a)) {
				lTTF.add("opsw_fcp_a");
			}

			if (xrdList.contains(xrd_fcp_b) || fdcList.contains(fdc_fcp_b) || tioList.contains(tio_fcp_b)) {
				lTTF.add("opsw_fcp_b");
			}
			hm.put(ptu, lTTF);
		}
		return hm;

	}

	HashMap<String, List<String>> genereateTTF(HashMap<String, List<String>> sourcePTU) {
		HashMap<String, List<String>> hm = genereateLaneGroup();
		HashMap<String, List<String>> generateTtfGroup = new HashMap<String, List<String>>();
		for (HashMap.Entry<String, List<String>> entry : sourcePTU.entrySet()) {
			List<String> generateTtfList = new ArrayList<String>();

			String module = entry.getKey().substring((entry.getKey().lastIndexOf("\\") + 1),
					entry.getKey().lastIndexOf(".c"));

			for (Iterator<String> iterator = entry.getValue().iterator(); iterator.hasNext();) {

				String str = (String) iterator.next();
				if (hm.containsKey(str)) {
					List<String> temp = hm.get(str);
					for (Iterator<String> iterator2 = temp.iterator(); iterator2.hasNext();) {
						String string = (String) iterator2.next();
						generateTtfList.add(str + ";" + entry.getKey().trim() + ";" + string);

					}

				}

			}
			generateTtfGroup.put(module, generateTtfList);
		}
		return generateTtfGroup;
	}

	HashMap<String, List<String>> sortTTF(HashMap<String, List<String>> ttf) {
		HashMap<String, List<String>> generateTtfGroup = new HashMap<String, List<String>>();

		for (HashMap.Entry<String, List<String>> entry : ttf.entrySet()) {
			List<String> generateTtfList = new ArrayList<String>();

			String module = entry.getKey();

			for (Iterator<String> iterator = entry.getValue().iterator(); iterator.hasNext();) {

				String str = (String) iterator.next();

				if (str.endsWith(";opsw_are")) {
					generateTtfList.add(str);
				}

			}
			for (Iterator<String> iterator = entry.getValue().iterator(); iterator.hasNext();) {

				String str = (String) iterator.next();

				if (str.endsWith(";opsw_fcp_a")) {
					generateTtfList.add(str);
				}

			}
			for (Iterator<String> iterator = entry.getValue().iterator(); iterator.hasNext();) {

				String str = (String) iterator.next();

				if (str.endsWith(";opsw_fcp_b")) {
					generateTtfList.add(str);
				}

			}
			
			generateTtfGroup.put(module, generateTtfList);
		}

		return generateTtfGroup;

	}
	
	HashMap<String, List<String>> groupTTF()
	{
		HashMap<String, List<String>> generateTtfGroup = new HashMap<String, List<String>>();

		
		
		for (Iterator<String> iterator = ttfList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			
			String[] word=string.split(";");
			String module = word[1].substring((word[1].lastIndexOf("\\") + 1),
					word[1].lastIndexOf(".c"));
			
			if(!generateTtfGroup.containsKey(module))
			{
				List<String> list=new ArrayList<String>();
				list.add(string);
				generateTtfGroup.put(module,list);

			}
			else
			{
				generateTtfGroup.get(module).add(string);
			}
			
			
			
		}
		
		
		
		
		return generateTtfGroup;

	}
	

}
