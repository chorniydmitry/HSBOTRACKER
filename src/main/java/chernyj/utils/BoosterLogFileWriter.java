package chernyj.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

import model.Booster;
import model.Card;
/**
 * @author Chernyj Dmitry
 *
 */
public class BoosterLogFileWriter {
	private Writer writer;
	private static final String HEADER = "Пак №,Карта1,Редкость1,Карта2,Редкость2,Карта3,Редкость3,Карта4,Редкость4,Карта5,Редкость5,Всего Пыли\n";

	public BoosterLogFileWriter(List<Booster> boosterList) {
		try {
			writer = new BufferedWriter(new FileWriter("pack_opening_" + boosterList.size() + "_" + ".csv"));
			writer.write(HEADER);
			for (int i = 0; i < boosterList.size(); i++) {
				String line = String.valueOf(i + 1) + ",";
				Booster b = boosterList.get(i);

				int dust = 0;
				for (Card card : b.getCards()) {
					String type = (card.isGolden()) ? "GOLDEN " : "";
					line += card.getName() + "," + type + card.getRarity() + ",";
					dust += DustCounter.getDust(card);
				}

				line += dust + "\n";
				writer.write(line);
			}

			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
