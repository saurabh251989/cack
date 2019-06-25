package com.sfcc.ttf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ValidateTTF {

	public ValidateTTF() {

	}

	static HashMap<String, String> getCommnet(HashMap<String, List<String>> ttf,
			HashMap<String, List<String>> targetTtf) {
		HashMap<String, String> comment = new HashMap<String, String>();

		for (HashMap.Entry<String, List<String>> entry : ttf.entrySet()) {

			String module = entry.getKey();

			List<String> temp = ttf.get(entry.getKey());
			List<String> temp1 = targetTtf.get(entry.getKey());
			
			if(temp1==null)
			{
				comment.put(module, "TTF is not found");
				continue;
			}
			java.util.Collections.sort(temp);
			java.util.Collections.sort(temp1);
			
			
			Set<String> unique = new HashSet<String>();
			Set<String> duplicate = findUnique(temp1, unique);

			String note = null;
			if (duplicate.size() == 0 && temp1!=null) {

				if (temp.size() == temp1.size()) {

					if (temp1.containsAll(temp)) {
						note = "TTF is Pass";
					} else {
						note = "No of TTF Is correct but not matched with PTU";

					}

				} else if (temp.size() > temp1.size()) {

					if (temp.removeAll(temp1)) {
						note = "Below PTU TTF is not found";
						for (Iterator<String> iterator = temp.iterator(); iterator.hasNext();) {
							String string = (String) iterator.next();
							note = note + "\n" + string;
						}

					} else {
						note = "TTF Is correct";
					}

				} else {
					if (temp1.removeAll(temp)) {

						note = "below extra Mapped TTF is found";
						for (Iterator<String> iterator = temp1.iterator(); iterator.hasNext();) {
							String string = (String) iterator.next();
							note = note + "\n" + string;
						}

					} else {

						note = "TTF Is correct";

					}

				}
			} else if(temp1!=null) {

				
				
				note = "Duplicate TTF is found";
			    for (Iterator<String> iterator = duplicate.iterator(); iterator.hasNext();) {
					String string = (String) iterator.next();
					note=note+"\n"+string;	
				}
			
			}
			else
			{
				note = "TTF is found";
				
			}
			comment.put(module, note);
		}
		return comment;
	}

	static Set<String> findUnique(List<String> ttfModule, Set<String> unique) {

		Set<String> duplicate = new HashSet<String>();
		for (Iterator<String> iterator = ttfModule.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();

			if (!unique.contains(string)) {
				unique.add(string);
			} else {
				duplicate.add(string);
			}

		}

		return duplicate;

	}

}
