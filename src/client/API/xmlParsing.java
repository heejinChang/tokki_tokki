package client.API;

//	한국환경공단_에어코리아_대기오염정보
//	한국환경공단_에어코리아_대기오염정보
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xmlParsing {
    //item 정보를 가져오는 메소드
    private static String getTagValue(String tag, Element ele) {
        NodeList nodeList = ele.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nodeList.item(0);
        if(nValue == null) {
            return null;
        }
        return nValue.getNodeValue();
    }// getTagValue

    public static String dustApi() {
        String serviceKey = "kZ65QKZFj03MYBUkNwDoFMZz1pinN34axKdfx4%2F%2FiijT2qmR9FYggDy16EEsP1n%2FFarNrhvm9oy5e1UTKYN%2BxQ%3D%3D";
        int page = 1; //페이지 초기값
        String result = " ";

        try {
            //  while (true) {
            //parsing할 url 지정
            String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=kZ65QKZFj03MYBUkNwDoFMZz1pinN34axKdfx4%2F%2FiijT2qmR9FYggDy16EEsP1n%2FFarNrhvm9oy5e1UTKYN%2BxQ%3D%3D&returnType=xml&numOfRows=100&pageNo=1&sidoName=%EC%84%9C%EC%9A%B8&ver=1.0";
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);
            //root tag
            doc.getDocumentElement().normalize();

            // System.out.println("Root Element : " + doc.getDocumentElement().getNodeName());

            // parsing tag

            NodeList nodeList = doc.getElementsByTagName("item");

            // System.out.println("파싱할 리스트 수 : " + nodeList.getLength());

            //for (int temp = 0; temp < ; temp++) {
            int rand = (int)(Math.random()*10);
            Node nNode = nodeList.item(rand);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                String a = "시도명 : " + getTagValue("sidoName", element);
                String b = "측정소명 : "+getTagValue("stationName", element);
                String date ="측정일시 : "+getTagValue("dataTime",element);
                String c = "측정망 정보 : " + getTagValue("stationName", element);
                //  String d = "일산화탄소 농도:" + getTagValue("coValue", element);
                //  String e = "오존 농도:" + getTagValue("o3Value", element);
                //  String f = "미세먼지(PM10)농도:" + getTagValue("pm10Value", element);
                String g = "통합대기환경지수 : " + getTagValue("khaiGrade", element);
                String grade = "대기 환경 등급 : 좋음:1 / 보통:2 / 나쁨:3 / 매우 나쁨:4";
                result ="<html>"+ a + " / " + b + " / "+ c+ "<br> </br>"+ date +" / " + g +"<br> </br>"+ "<br> </br>"+ grade + "</html>";
                System.out.println(result);
            }//fo
            //}//while

        } catch (Exception e) {

            e.printStackTrace();

        }
        return result;
    }
}



