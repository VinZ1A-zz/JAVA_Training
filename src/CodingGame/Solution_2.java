package CodingGame;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/

// Vortex - coding game - in progress
class Solution_2 {

	enum Morse {

		A(".-"), B("-..."), C("-.-."), D("-.."), E("."), F("..-."), G("--."), H("...."), I(".."), J(".---"), K(
				"-.-"), L(".-.."), M("--"), N("-."), O("---"), P(".--."), Q("--.-"), R(
						".-."), S("..."), T("-"), U("..-"), V("...-"), W(".--"), X("-..-"), Y(" -.--"), Z("--..");

		String _val;
		static TreeMap<Character, String> _codes = new TreeMap<Character, String>();

		Morse(String iStr) {
			_val = iStr;
		}

		// init _codes table (A -> .- ect...)
		static void init() {
			if (_codes.isEmpty()) {
				for (Morse val : Morse.values()) {
					_codes.put(val.toString().charAt(0), val._val);
				}
			}
		}

		// translate a Word
		static String translate(String iStr) {
			StringBuilder outStr = new StringBuilder();
			for (int i = 0; i < iStr.length(); i++) {
				char aChar = iStr.charAt(i);
				outStr.append(_codes.get(aChar));
			}
			return outStr.toString();
		}
	}

	static class Message {

		ArrayList<String> _words = new ArrayList<String>();

		Message(String iStr) {
			_words.add(iStr);
		}

		Message() {

		}

		boolean isSingleWord() {
			return (_words.size() == 1);
		}
	}

	static class Pod {

		String _strToMatch = null; // stays null when it is a final leaf
		// whatever this pod is matching (could be single word, or a list of
		// messages)
		ArrayList<Message> _matchingMessages = new ArrayList<Message>();
		ArrayList<Node> _children = new ArrayList<Node>();
		// empty when final leaf
		HashMap<String, String> _potentialMatchingWords = new HashMap<String, String>();

		@Override
		public String toString() {
			return "_strToMatch: " + _strToMatch; // + matching messages
		}
	}

	// // a Pod which already exists.
	// static class LinkedPod extends Pod {
	// // same stuff.
	// }

	static class Node {

		Pod[] _pods = new Pod[3]; // 0: left, 1:mid, 2: right
		Pod _parent = null; // needed?
		int _level = 0; // debug
	}

	public static void doIt() {
		Scanner in = new Scanner(System.in);
		String L = in.next(); // Morse line
		formatAsInput(L); // DEBUG
		int N = in.nextInt(); // up to 9444 !...
		formatAsInput(String.valueOf(N)); // DEBUG

		Morse.init(); // defines treeset (A --> '.-' )

		HashMap<String, String> aWords = new HashMap<String, String>();
		int counter = 0; // DEBUG 1995
		for (int i = 0; i < N; i++) {
			String W = in.next();
			String translatedW = Morse.translate(W);
			// filter out Words which would not match anyway
			if (L.contains(translatedW)) {
				aWords.put(W, translatedW);

				counter++;
				if (counter >= 101 && counter < 200) {
					formatAsInput(W); // DEBUG
				}
			}
			// if (i >= 143 && i < 200) {
			// formatAsInput(W); // DEBUG
			// }
		}

		debugln("potential words " + counter);

		// ArrayList<Node> winningNodes = new ArrayList<Node>();
		Pod root = new Pod();
		root._strToMatch = L;
		// root._dist = 0;
		root._potentialMatchingWords = aWords;
		Node rootNode = new Node();
		rootNode._pods[1] = root;
		Queue<Node> BFSNodes = new LinkedList<Node>();
		BFSNodes.add(rootNode);

		while (!BFSNodes.isEmpty()) {
			buildSubNodes(BFSNodes);
		}

		System.out.println("yepaa");
	}

	// Dictionary of Pods : eg. ".-..-.--." --> known pod
	static TreeMap<String, Pod> _knownPods = new TreeMap<String, Pod>();

	static void buildSubNodes(Queue<Node> ioNodes) {
		Node aNode = ioNodes.poll();
		// debugln("processing pod " + aNode._pods[0]);
		// each Pod (L, Mid, R) in Node
		for (Pod aPod : aNode._pods) {
			if (aPod == null) // we don't always have both the right and left
								// pods
				continue;
			// skip Pod if not Master Pod. eg. is already listed in _knownPods
			Pod aKNownPod = aPod._strToMatch != null ? _knownPods.get(aPod._strToMatch) : null;
			if (aKNownPod != null && aKNownPod._matchingMessages.size() > 0) { // pod
																				// is
																				// already
																				// processed
																				// or
																				// is
																				// a
																				// Word
				continue;
			}
			// Which word is matching each Pod ? ( Note: we already know each
			// word will match!)
			for (Entry<String, String> transWord : aPod._potentialMatchingWords.entrySet()) {
				Node aChildNode = new Node();
				aChildNode._level = aNode._level + 1;
				// add parent Nod in _parent of child node
				aChildNode._parent = aPod;
				// mid Pod
				Pod midPod = _knownPods.get(transWord.getValue());
				if (midPod == null) {
					// create mid Pod as final (contains a Word in
					// _matchingMessages )
					midPod = new Pod();
					_knownPods.put(transWord.getValue(), midPod); // add in
																	// _knownPods
				}

				// TODO - only add if Message does not already exists xxxxx
				// (could this happen?)
				midPod._matchingMessages.add(new Message(transWord.getKey()));
				// Note : _potentialMatchingWords is left empty to show that
				// this is a final leaf (no more
				// matches to perform)
				// also, strToMatch is empty (successful leaf pod)
				// incorporate in new child Node
				aChildNode._pods[1] = midPod;
				// building left and right Pods
				int index = aPod._strToMatch.indexOf(transWord.getValue());
				// ----------------- there is a left Pod
				// -------------------------------
				if (index > 0) {
					String leftWord = aPod._strToMatch.substring(0, index);
					Pod leftPod = _knownPods.get(leftWord);
					if (leftPod == null) {
						leftPod = new Pod();
						// add in _knownPods
						_knownPods.put(leftWord, leftPod);
						leftPod._strToMatch = leftWord;
						// TODO: optimize
						leftPod._potentialMatchingWords = aPod._potentialMatchingWords;
					}
					// TODO : will need to merge with existing list
					// leftPod._potentialMatchingWords =
					// getPotentialWords(aPod._potentialMatchingWords,
					// leftWord);
					aChildNode._pods[0] = leftPod;// incorporate in new child
													// Node
				}
				// -------------------------- there is a right Pod
				// -----------------------
				// TODO - merge logic between L and R (function)
				if (index + transWord.getValue().length() < aPod._strToMatch.length()) {

					String rightWord = aPod._strToMatch.substring(index + transWord.getValue().length());
					Pod rightPod = _knownPods.get(rightWord);
					if (rightPod == null) {
						rightPod = new Pod();
						// add in _knownPods
						_knownPods.put(rightWord, rightPod);
						rightPod._strToMatch = rightWord;
						// TODO: optimize
						rightPod._potentialMatchingWords = aPod._potentialMatchingWords;
					}
					// TODO : will need to merge with existing list
					// leftPod._potentialMatchingWords =
					// getPotentialWords(aPod._potentialMatchingWords,
					// leftWord);
					// incorporate in new child Node
					aChildNode._pods[2] = rightPod;

				}
				// TODO - matching messages in parent pods --- > needed?
				// eg. if no left nor right pods, add them in parent pod
				// if (aChildNode._pods[0] == null & aChildNode._pods[2] ==
				// null) {
				//
				// // loop to update all as far up as possible
				//
				// }
				// add in _children of parent Pod
				aPod._children.add(aChildNode);
				debugln("adding node lev " + aChildNode._level + " with pod0 " + aChildNode._pods[0] + ",pod1 "
						+ aChildNode._pods[1] + ",pod2 " + aChildNode._pods[2]);
				ioNodes.add(aChildNode);
			} // end loop on _potentialMatchingWords
		} // end loop on all pods for this Node
	}

	static HashMap<String, String> getPotentialWords(HashMap<String, String> iCurPotWords, String iToMatch) {
		HashMap<String, String> result = new HashMap<String, String>();
		for (Entry<String, String> aEntry : iCurPotWords.entrySet()) {
			if (iToMatch.contains(aEntry.getValue())) {
				result.put(aEntry.getKey(), aEntry.getValue());
			}
		}

		return result;
	}

	// debug method - discard!
	static private Vector<String> getData() {
		Vector<String> aInput = new Vector<String>();

		// detect letter
		// aInput.add("-.-");
		// aInput.add("6");
		// aInput.add("A");
		// aInput.add("B");
		// aInput.add("C");
		// aInput.add("HELLO");
		// aInput.add("K");
		// aInput.add("WORLD");

		// detect word
		// aInput.add("--.-------..");
		// aInput.add("5");
		// aInput.add("GOD");
		// aInput.add("GOOD");
		// aInput.add("MORNING");
		// aInput.add("G");
		// aInput.add("HELLO");

		// detect message
		aInput.add("......-...-..---.-----.-..-..-..");
		aInput.add("5"); // 7
		aInput.add("HELL");
		aInput.add("HELLO");
		aInput.add("OWORLD");
		aInput.add("WORLD");
		aInput.add("TEST");
		// aInput.add("HE");
		// aInput.add("LLO");

		// long stuff
		// aInput.add(".--....--.---.--..--.....-.-...---..--...---..--.-.---..--.-.---..--.-.......-.-.......-.-.----.-.---");
		// aInput.add("24");
		// aInput.add("PIANO");
		// aInput.add("PANIER");
		// aInput.add("BOUBOU");
		// aInput.add("COUCOU");
		// aInput.add("CHICHI");
		// aInput.add("COCO");
		//
		// aInput.add("PIXANO");
		// aInput.add("PANXIER");
		// aInput.add("BOUBXOU");
		// aInput.add("CXOUCOU");
		// aInput.add("CXHICHI");
		// aInput.add("COCOX");
		//
		// aInput.add("PIYANO");
		// aInput.add("PANYIER");
		// aInput.add("BYOUBOU");
		// aInput.add("CYOUCOU");
		// aInput.add("CHICYHI");
		// aInput.add("COCYO");
		//
		// aInput.add("PZIANO");
		// aInput.add("PZANIER");
		// aInput.add("BOUBOZU");
		// aInput.add("COUCZOU");
		// aInput.add("CZHICHI");
		// aInput.add("CZOCO");

		// aInput.add(".-...-.-...--......--....------.-.....---..--.-.----...-----.-.........-..--.-.-.----....--...-.--.--.--.--...-.-..--....--.--.-....--.-.-..--..-...-..-...-.......-.......-....--.--...-.-.......----....-.-.---..-.-.-.....-.-..----.--...-....--.-..-..-...-.........---...-.-.--..-.----....-..-.....--..-.-.-...........--.-.......--...-....---...-..---..-..--.--.....-.-.-..-.-.----.......-..-..-.----.--.-.-.---.-...-.....-....---..-..-..-----.-......-.-..---.-....--.-..-..--..-....-........-.-.....--...-....----.-......-...---.-...-.....-.-.-...----..-...........-.--.-.-.-....-..-..-.....-.-...-....-....-.-.-.-..--.---..-.-..-...--.-..--..-..-.--........-.-..-.-.----...---..-...-.-..-.-..--.-.-.-......-........--.-..-.-......---.-..-..-...--.-..-.-.........-...-..-...-.....--.-..-....--.----.-.-.-..-.--..----...--..-.-.------.....-..-.....----...--...--.-..-.-........--.-..-..--..-..-.-..-.--..--.-.-....-...-.-.----...-----.-.........--.-.----......-...-.-.-.-------..-....--..-.-.-...-.-.......-.-.-........-.-..-...-..-..-.--.-..-.-.-.-..-...-.-....--...-..-...-..-.-.-.....-..--.-..-..-...-.-.-...-..-.-.........---..--...-.--..--...-...-..-..--....----.-..--..-..-.--........-....--...-......-.......--.-.......--....-.--...---.-.......--..-...-....-....-...-.-..-.-.-------..--...-.-..--..----..--.--.--.-.--..-.......-....-...-...-......--..-.-.-...-.-...-.-..-------.-.-.-...-.-..-..-...-.-.--.-.-...-..--..-..--....----.-..--..-..-.--.......--.-..-..-.-..--....----.-...--.....----...--.-..-...--...-........--.-.-......----.-............-.---.....-.-.....-.-.-------.-.-.-..-..-.-..-....-...-..-...-.-.--.-.--.-...--..-.-...--.-...-...-..-.....-..-.-.-.---.-..-.-...------..-.-.-........-..-.--.-..-.-.-.-.......-.-..-.-..--..----..-.-.......-..-...-..-.-.-.....--..-.-..-....-..-.-...--......-...-...-.....----.-....-.........-.-..-....-...---------.-.-...-..---..-.-..--..--.-.-------..--...-.-..--..----....--.-.-...-..--.....-..-..--....----.-..--..-..-.--.........--..-.-.--..-...-..--.-.....-.-....--..-.-....--..---..--.-....--..-.-....--...--.---..-...-.--.-.-..-........-.-....--...-........--..-.-..-...-...-...--..----.-.-......-...-....-...--....--.-...-......---.-.....-.----.-----.-.-.-.....-...--...-.........--..-.-...--.....-..-.--.-.-..-..-....--..--.--...-...--.....-...------.-....--..-.-.--...-......--.-..-..-......-...-.-..--..........----..--....-..-...-.-.--..-.-..-...-...---....-.-....--...---.--..-...-.-..---..-.---..--..-...--.-.......-...-...-.-..--...--....-....--.-..-.-....--.-.----.--.----...---..-...---..--...-...-.-.---.....-...-......-.--.....--.......-....-.-..----........-....-.-.-......-.....-..-...--.-...-----.--..-...-.-.---..---.--.....-...-..-.-...-..--..----.-....-...-..........-..........------.-.---.-...-.-..-..-......-.-..........-.---.-.......-...-......--.-..-..-..--.-..-.....-..--.-...--.-..----.....--..-.....-...-.--..-....-.--..-.-.--.........-.--..----....--.-.-...-..--.....-.-.-.---.-...-..--.-.-.....-..--.--.--.-.-.--..-....-....-.-----.-.....--.-..-..-.....-.-...-.-....-.-..--....-.-...-.--..--...-...-...-.-.-..-..-...-...--.-.-.-.....-.-...-..-.--.-..-.-.-.-..-..--..........----..-..-.------..-.-.-........-..-.-...-.-.-..-.......-..-.--.-..-.-.-.-.....-......-..-......-..--.-..-..-...-.-..-.-.-..---....-...---.-.-...-..--.-.-.-.-......--.-...----.-.-.-.-.---.-....--...-..-...-..-.-.-.--..----.----........-...-......-.....----.-.--..-....-.....--...-...........--.-..-..--..-.-.--.-..-..-..--.-..-.....--..--.--....--.-.....-..-.-..--.-.--....--...-......-...-..-.....---....--..--..-......-.-..-....-.-....-....-.-.-..----.-....-...-.-...---.--...-.-..-........-......-....--..-..--..-.--....-...-.-.---..-.-...--.-.--.-.-------..--...--.-..-..-..-.-----.-......-....---..-....--..-.-.-..---.-....--.-..-..-...--.-..-.--..--.-...---.--.-..-...-..-..........--.-..-..--..-.-.....-..-..-..--...--.---..-.-.-......-.-....-.---..-...-...-.-.....-.-....-...---.-.-...-..--.-.-.-.-......--.-...-...--....-...-.------....-....--..-.--..--.--...-..----.-.-...-..-..-..--.-.-..");
		// aInput.add(String.valueOf(265 - 240 - 1)); // 439 - 240 - 1
		//
		// aInput.add("ORESONTTC");
		// aInput.add("UDHRD");
		// aInput.add("MSNLV");
		// aInput.add("ILGJN");
		// aInput.add("GTHRO");
		// aInput.add("KKPRT");
		// aInput.add("ESTI");
		// aInput.add("IVSKM");
		// aInput.add("BXDMQ");
		// aInput.add("FLUXK");
		// aInput.add("CTUEL");
		// aInput.add("RJOKE");
		// aInput.add("CCSRT");
		// aInput.add("ZRRUIN");
		// aInput.add("NWCIS");
		// aInput.add("SSJEI");
		// aInput.add("NBUDR");
		// aInput.add("VTMLP");
		// aInput.add("FHTAE");
		// aInput.add("WEARA");
		// aInput.add("DNKHH");
		// aInput.add("NMCGZ");
		// aInput.add("IEMORSEDEPU");
		// aInput.add("SLESSIGN");
		// aInput.add("TRMEW");
		// aInput.add("DESMETTEURSIN");
		// aInput.add("LUSENM");
		// aInput.add("STFSV");
		// aInput.add("XEEDR");
		// aInput.add("UEEHU");
		// aInput.add("ORSEQUELEURINDI");
		// aInput.add("ASHLUMINE");
		// aInput.add("MRIAB");
		// aInput.add("MMEDENOMBREUXR");
		// aInput.add("PHIEPARMI");
		// aInput.add("RLAFRQ");
		// aInput.add("PTMNE");
		// aInput.add("DBAEM");
		// aInput.add("NLAROUMA");
		// aInput.add("IWTET");
		// aInput.add("CSTBO");
		// aInput.add("FHSVD");
		// aInput.add("TFEUXDITSLETT");
		// aInput.add("MESL");
		// aInput.add("PRMTE");
		// aInput.add("FWPRE");
		// aInput.add("EAEZW");
		// aInput.add("DHUILEMORSEES");
		// aInput.add("CLJEX");
		// aInput.add("UMRRK");
		// aInput.add("DPMXS");
		// aInput.add("NTIUH");
		// aInput.add("BLPNHR");
		// aInput.add("IDDVA");
		// aInput.add("CHRTS");
		// aInput.add("SSSOX");
		// aInput.add("SCOMME");
		// aInput.add("KXTAB");
		// aInput.add("PMGNF");
		// aInput.add("ICKIR");
		// aInput.add("RVNWS");
		// aInput.add("XEENP");
		// aInput.add("UEOUCO");
		// aInput.add("TTOID");
		// aInput.add("NGURS");
		// aInput.add("XRNDX");
		// aInput.add("BWMAS");
		// aInput.add("MJECF");
		// aInput.add("UENCEDEKHZ");
		// aInput.add("RATEM");
		// aInput.add("BTQSU");
		// aInput.add("VTDFT");
		// aInput.add("VNIJR");
		// aInput.add("FBWVG");
		// aInput.add("THULK");
		// aInput.add("DFKTO");
		// aInput.add("WFRTE");
		// aInput.add("CEBTV");
		// aInput.add("UNDOM");
		// aInput.add("MMTOF");
		// aInput.add("OONME");
		// aInput.add("LDEUF");
		// aInput.add("GLAIS");
		// aInput.add("ZKLHC");
		// aInput.add("VMKRN");
		// aInput.add("UEDVK");
		// aInput.add("BRGRC");
		// aInput.add("NECTN");
		// aInput.add("ENSZB");
		// aInput.add("XPARALLLEMENTAUCO");
		// aInput.add("PKENW");
		// aInput.add("RTAIZ");
		// aInput.add("RQFTC");
		// aInput.add("CIVILPOUR");
		// aInput.add("ENTLESBANDE");
		// aInput.add("XPPZA");
		// aInput.add("ESONDE");
		// aInput.add("NVTAN");
		// aInput.add("ESOPRATEURSAFIN");
		// aInput.add("LEGHM");
		// aInput.add("JNMAA");
		// aInput.add("HEMBW");
		// aInput.add("XFSRB");
		// aInput.add("SOPUR");
		// aInput.add("QEEHT");
		// aInput.add("OKDEI");
		// aInput.add("DEEZR");
		// aInput.add("OMMTC");
		// aInput.add("LPIN");
		// aInput.add("ZTSDI");
		// aInput.add("RWRKX");
		// aInput.add("ZSNAD");
		// aInput.add("FIXTH");
		// aInput.add("ENNENTP");
		// aInput.add("ITIMEPARCERTAINST");
		// aInput.add("IMXIF");
		// aInput.add("LSSPF");
		// aInput.add("NMXBT");
		// aInput.add("EKMKT");
		// aInput.add("PBELT");
		// aInput.add("GDIKA");
		// aInput.add("SEGES");
		// aInput.add("VLHTS");
		// aInput.add("ESASS");
		// aInput.add("NDTHR");
		// aInput.add("GCHAS");
		// aInput.add("EXIGEPOUR");
		// aInput.add("FNXEF");
		// aInput.add("LPIBEI");
		// aInput.add("IVEHO");
		// aInput.add("EURSDETL");
		// aInput.add("EUTTRETRANSPOR");
		// aInput.add("FRANCE");
		// aInput.add("CNATM");
		// aInput.add("QVUEW");
		// aInput.add("DTLVU");
		// aInput.add("SVGAS");
		// aInput.add("QIRHD");
		// aInput.add("ENDWE");
		// aInput.add("VNXON");
		// aInput.add("FRENCESM");
		// aInput.add("ANXRV");
		// aInput.add("EDEDU");
		// aInput.add("DBAZM");
		// aInput.add("DHNID");
		// aInput.add("LDGGL");
		// aInput.add("FWOSV");
		// aInput.add("XOMCK");
		// aInput.add("VCLWE");
		// aInput.add("RNASU");
		// aInput.add("MQDSG");
		// aInput.add("SALWT");
		// aInput.add("JGSUE");
		// aInput.add("NDEURSRA");
		// aInput.add("POTAB");
		// aInput.add("PDNTO");
		// aInput.add("CEXEI");
		// aInput.add("HIXTI");
		// aInput.add("AREXE");
		// aInput.add("AORMM");
		// aInput.add("EJIZE");
		// aInput.add("MRFER");
		// aInput.add("WRGLH");
		// aInput.add("HIOSC");
		// aInput.add("ESMTRE");
		// aInput.add("FXWEA");
		// aInput.add("AISONSRADIOTL");
		// aInput.add("MBREUXAU");
		// aInput.add("VRAMA");
		// aInput.add("UCONGO");
		// aInput.add("SFHDA");
		// aInput.add("DESCHANGERDESI");
		// aInput.add("SPXRP");
		// aInput.add("DACEO");
		// aInput.add("HMRBK");
		// aInput.add("RIBRA");
		// aInput.add("IURFI");
		// aInput.add("DELAMARI");
		// aInput.add("KJOXD");
		// aInput.add("RBIWA");
		// aInput.add("ILUTI");
		// aInput.add("VIATR");
		// aInput.add("HUIAR");
		// aInput.add("NFUTD");
		// aInput.add("LTFHA");
		// aInput.add("RDESCOMMUN");
		// aInput.add("TXIIM");
		// aInput.add("QRPIT");
		// aInput.add("GVJEE");
		// aInput.add("PFFIK");
		// aInput.add("GCHSV");
		// aInput.add("ORTA");
		// aInput.add("IAIS");
		// aInput.add("QDEKK");
		// aInput.add("SHRPM");
		// aInput.add("ZIFDM");
		// aInput.add("MTDCS");
		// aInput.add("IBDET");

		return aInput;
	}

	static boolean _debug = false;
	static boolean _fromEclipse = false;

	public static void main(String args[]) {
		if (args.length != 0) {
			_debug = true;
			// _fromEclipse = true;
			String aInput = "";
			for (String data : getData()) {
				aInput += data + "\r\n";
			}
			System.setIn(new ByteArrayInputStream(aInput.getBytes()));
		}
		doIt();

	}

	static void debugln(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}

	static void formatAsInput(String iStr) {
		if (_debug) {
			System.err.println("aInput.add(\"" + iStr + "\");");
		}
	}
}