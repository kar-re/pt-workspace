import java.util.ArrayList;
public class Questions {
	void initQuestions(ArrayList<Question> list) {
		String[] alt1 = {"Hej", "hej2", "hej3", "hej4"};
		String[] bev = {"90210", "90120", "80230", "22241"};
		list.add(new Question("", "Vilka fem nummer var en del av titeln i tv-serien Beverly Hills .....?", bev, 1));
		String[] bulg = {"Bulgarien", "Ukraina", "Kroatien", "Ungern"};
		list.add(new Question("plong", "Vilket land gränsar ej mot Rumänien?", bulg, 3));
		String[] wasser = {"4 Grader C", "0 Grader C", "-4 Grader C", "-200 Grader C"};
		list.add(new Question("Gurka", "Vid vilken temperatur är vatten som tyngst(högst densitet)?", wasser, 1));
		String[] euro = {"1 seger", "2 segrar", "3 segrar", "4 segrar"};
		list.add(new Question("Gurka", "Hur många segrar har Norge i Eurovision?", euro, 3));
		String[] kokboken = {"Charlotte Berg", "Cajsa Warg", "Ebba Segerborg", "Lina Klint"};
		list.add(new Question("gurk", "Vem gav 1755 ut kokboksklassikern Hjelpreda I hushållningen för unga Fruentim(b)er", kokboken, 2));
		String[] kung = {"Henry II", "Henry V", "Henry I", "Richard I"};
		list.add(new Question("gurk", "Vilken kung var gift med Eleonora av Akvitanien?", kung, 1));
		String[] tred = {"Träd", "Grönsaker", "Blommor", "Säd"};
		list.add(new Question("gurk", "Om du planterar frön från Quercus Robur – vad kommer att börja växa då?", tred, 1));
		String[] vult = {"Volt", "Hertz", "Pascal", "Ohm"};
		list.add(new Question("gurk", "Vilken vetenskaplig enhet är döpt efter en italiensk adelsman?", vult, 3));
		String[] hest = {"Kentucky Derby", "Preakness Stakes", "Arlington Million", "Belmont Stakes"};
		list.add(new Question("gurk", "Vilken av dessa är inte en av hästkapplöpningarna som ingår i American Triple Crown?", hest, 3));
		String[] box = {"Terry Spinks", "Don Cockell", "Freddie Mills", "Bombardier Billy Wells"};
		list.add(new Question("gurk", "Vad hette boxaren som sågs slå på gongongen i inledningen till filmbolaget J. Arthur Rank filmer?", box, 4));
		
	}
}
