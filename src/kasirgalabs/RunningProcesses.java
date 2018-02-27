package kasirgalabs;


import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.common.collect.Sets;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;
import java.util.Arrays;

public class RunningProcesses extends JFrame implements Runnable{

	public static JFrame frame3 = null;
	List<String> kapatilanUygulamalar = new ArrayList<String>();
	List<String> acilanUygulamalar = new ArrayList<String>();
        List<String> blockedAppsList = new ArrayList<String>();
	
	public List<String> getKapatilan(){
		return this.kapatilanUygulamalar;
	}
	public List<String> getAcilan(){
		return this.acilanUygulamalar;
	}

	static interface User32 extends StdCallLibrary {
		User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);

		interface WNDENUMPROC extends StdCallCallback {
			boolean callback(Pointer hWnd, Pointer arg);
		}

		boolean EnumWindows(WNDENUMPROC lpEnumFunc, Pointer userData);
		int GetWindowTextA(Pointer hWnd, byte[] lpString, int nMaxCount);
		Pointer GetWindow(Pointer hWnd, int uCmd);
	}

	public static List<String> getAllWindowNames() {
		final List<String> windowNames = new ArrayList<String>();
		final User32 user32 = User32.INSTANCE;
		user32 .EnumWindows(new User32.WNDENUMPROC() {

			public boolean callback(Pointer hWnd, Pointer arg) {
				byte[] windowText = new byte[512];
				user32.GetWindowTextA(hWnd, windowText, 512);
				String wText = Native.toString(windowText).trim();
				if (!wText.isEmpty()) {
					windowNames.add(wText);
				}
				return true;
			}
		}, null);

		return windowNames;
	}

	public RunningProcesses(String blockedApps){
            
            //System.out.println("Blocked apps: " + blockedApps);
            blockedAppsList = Arrays.asList(blockedApps.split(","));
            for(String element : blockedAppsList) {
                System.out.println(element);
                
            }
	}

	public static void main(String[] args) {
            
	}

	public void run() {
		 System.out.println("rp run");	
			List<String> chromeList = new ArrayList<String>();
			List<String> firefoxList = new ArrayList<String>();
			List<String> explorerList = new ArrayList<String>();
			List<String> edgeList = new ArrayList<String>();
			

			
			List<String> winNameList = getAllWindowNames();
			for (String winName : winNameList) {
				System.out.println(winName);
				if(winName.contains("Google Chrome")){
					System.out.println(winName);
					if(chromeList.size()==0){
						chromeList.add(winName);
					}
					else if(!(chromeList.get(0).equals(winName))){
						chromeList.add(winName);
					}

				}
				if(winName.contains("Mozilla Firefox")){
					System.out.println(winName);
					firefoxList.add(winName);
				}
				if(winName.contains("Internet Explorer")){
					System.out.println(winName);
					if(explorerList.size()==0){
						explorerList.add(winName);
					}
					else if(!(explorerList.get(0).equals(winName))){
						explorerList.add(winName);
					}
				}
				if(winName.contains("Microsoft Edge")){
					System.out.println(winName);
					if(edgeList.size()==0){
						edgeList.add(winName);
					}
					else if(!(edgeList.get(0).equals(winName))){
						edgeList.add(winName);
					}
				}

			}
			
			String eskiChromeSekme = "";
			String eskiFirefoxSekme = "";
			
			String yeniChromeSekme = "";
			String yeniFirefoxSekme = "";
			
			String eskiExplorerSekme = "";
			String yeniExplorerSekme = "";
			
			String eskiEdgeSekme = "";
			String yeniEdgeSekme = "";
			
			
			if(chromeList.size()>1){
				System.out.println("1'den fazla Google Chrome penceresi acik. Sadece 1 pencere acik olacak sekilde digerlerini kapatin.");
				JOptionPane.showMessageDialog(null, 
						"1'den fazla chrome penceresi acik. Sadece 1 pencere acik olacak sekilde digerlerini kapatin.", 
						"Hata", JOptionPane.ERROR_MESSAGE);
				
			}
			else if(chromeList.size()==1){
				eskiChromeSekme = chromeList.get(0);
			}
			
			if(firefoxList.size()>1){
				System.out.println("1'den fazla firefox penceresi acik. Sadece 1 pencere acik olacak sekilde digerlerini kapatin.");
				JOptionPane.showMessageDialog(null, 
						"1'den fazla Mozilla Firefox penceresi acik. Sadece 1 pencere acik olacak sekilde digerlerini kapatin.", 
						"Hata", JOptionPane.ERROR_MESSAGE);
			}
			else if(firefoxList.size()==1){
				eskiFirefoxSekme = firefoxList.get(0);
			}
			
			if(explorerList.size()>1){
				System.out.println("1'den fazla explorer penceresi acik. Sadece 1 pencere acik olacak sekilde digerlerini kapatin.");
				JOptionPane.showMessageDialog(null, 
						"1'den fazla Internet Explorer penceresi acik. Sadece 1 pencere acik olacak sekilde digerlerini kapatin.", 
						"Hata", JOptionPane.ERROR_MESSAGE);
				for (String expName : explorerList) {
					System.out.println(expName);
				}
			}
			else if(explorerList.size()==1){
				eskiExplorerSekme = explorerList.get(0);
			}
			
			if(edgeList.size()>1){
				System.out.println("1'den fazla edge penceresi acik. Sadece 1 pencere acik olacak sekilde digerlerini kapatin.");
				JOptionPane.showMessageDialog(null, 
						"1'den fazla Microsoft Edge penceresi acik. Sadece 1 pencere acik olacak sekilde digerlerini kapatin.", 
						"Hata", JOptionPane.ERROR_MESSAGE);
				for (String edgeName : edgeList) {
					System.out.println(edgeName);
				}
			}
			else if(edgeList.size()==1){
				eskiEdgeSekme = edgeList.get(0);
			}
			
			
			
			while(true){
				List<String> winNameList2 = getAllWindowNames();
		    	  Set foo = new HashSet(winNameList);
		    	  Set foo2 = new HashSet(winNameList2);

		    	  //List<String> fark = Sets.symmetricDifference(foo, foo2);
		    	  
		    	  Set fark = Sets.symmetricDifference(foo, foo2);
		    	  
		    	  //System.out.println("Fark : " + fark);
		    	  //System.out.println(fark.toString());
		    	  if(foo.size()>foo2.size()){
		    		  System.out.println("Kapatilan uygulamalar: " + fark );
		    		  kapatilanUygulamalar.add(fark.toString());
		    		  
		    	  }
		    	  else if(foo2.size()>foo.size()){
		    		 System.out.println("Acilan uygulamalar: " + fark);
		    		 acilanUygulamalar.add(fark.toString());
		    	  }
		    	  
				//winNameList = getAllWindowNames();
				for (String winName : winNameList) {
					if(winName.contains("Google Chrome")){
						yeniChromeSekme = winName;

					}
					if(winName.contains("Mozilla Firefox")){
						yeniFirefoxSekme = winName;

					}
					if(winName.contains("Internet Explorer")){
						yeniExplorerSekme = winName;

					}
					if(winName.contains("Microsoft Edge")){
						yeniEdgeSekme = winName;

					}
                                        
                                        /*
					if(winName.contains("pdf")){
						JOptionPane.showMessageDialog(null, 
								"PDF dosyasi acik !", "Hata", JOptionPane.ERROR_MESSAGE);
		            	 System.out.println("Pdf dosyasi acik !  " + winName);
		             }
		             
					if(winName.contains("Office")){
						JOptionPane.showMessageDialog(null, 
								"Office dosyasi acik !", "Hata", JOptionPane.ERROR_MESSAGE);
						System.out.println("Office dosyasi acik ! " + winName);
					}
					
					if(winName.contains("Word")){
						JOptionPane.showMessageDialog(null, 
								"Word dosyasi acik !", "Hata", JOptionPane.ERROR_MESSAGE);
						System.out.println("Word dosyasi acik ! " + winName);
					}
					if(winName.contains("Powerpoint")){
						JOptionPane.showMessageDialog(null, 
								"Powerpoint dosyasi acik !", "Hata", JOptionPane.ERROR_MESSAGE);
						System.out.println("Powerpoint dosyasi acik ! " + winName);
					}
					if(winName.contains("jpg") || winName.contains("png")){
						JOptionPane.showMessageDialog(null, 
								"Fotograf dosyasi acik !", "Hata", JOptionPane.ERROR_MESSAGE);
						System.out.println("Fotoğraflar dosyasi acik ! " + winName);
					}
					*/
                                        for(String element : blockedAppsList) {
                                           
                                            if(winName.toLowerCase().contains(element.toLowerCase())){
                                                //JOptionPane.showMessageDialog(null, 
								//element +" dosyasi acik !", "Hata", JOptionPane.ERROR_MESSAGE);
						System.out.println(element +" dosyasi acik ! " + winName);
                                                closeApp(element);
                                            }
                                            
                                        }
		             

				}
				if(!(eskiChromeSekme.equals(yeniChromeSekme))){

//					frame3 = new JFrame();
//					frame3.setVisible(true);
//					frame3.setLocationRelativeTo(null);
//					frame3.setSize(300, 225);
//					frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//					JLabel label = new JLabel("Google Chrome sekmesi degistirildi");
//					JPanel panel = new JPanel();
//					frame3.add(panel);
//					panel.add(label);
					JOptionPane.showMessageDialog(null, "Google Chrome sekmesi degistirildi", "Hata", JOptionPane.ERROR_MESSAGE);
					System.out.println("Google Chrome sekmesi degistirildi !!!");

					System.out.println("Eski sekme: " + eskiChromeSekme);
					System.out.println("Yeni sekme: " + yeniChromeSekme);
					eskiChromeSekme = yeniChromeSekme;


				}
				if(!(eskiFirefoxSekme.equals(yeniFirefoxSekme))){

					JOptionPane.showMessageDialog(null, "Mozilla Firefox sekmesi degistirildi", "Hata", JOptionPane.ERROR_MESSAGE);
					System.out.println("Mozilla Firefox sekmesi degistirildi !!!");

					System.out.println("Eski sekme: " + eskiFirefoxSekme);
					System.out.println("Yeni sekme: " + yeniFirefoxSekme);
					eskiFirefoxSekme = yeniFirefoxSekme;


				}
				if(!(eskiExplorerSekme.equals(yeniExplorerSekme))){

					JOptionPane.showMessageDialog(null, "Internet Explorer sekmesi degistirildi", "Hata", JOptionPane.ERROR_MESSAGE);
					System.out.println("Explorer sekmesi degistirildi !!!");

					System.out.println("Eski sekme: " + eskiExplorerSekme);
					System.out.println("Yeni sekme: " + yeniExplorerSekme);
					eskiExplorerSekme = yeniExplorerSekme;


				}
				if(!(eskiEdgeSekme.equals(yeniEdgeSekme))){

					JOptionPane.showMessageDialog(null, "Microsoft Edge sekmesi degistirildi", "Hata", JOptionPane.ERROR_MESSAGE);
					System.out.println("Microsoft Edge sekmesi degistirildi !!!");

					System.out.println("Eski sekme: " + eskiEdgeSekme);
					System.out.println("Yeni sekme: " + yeniEdgeSekme);
					eskiEdgeSekme = yeniEdgeSekme;


				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					  //throw new RuntimeException(e);
				}
				
				winNameList = winNameList2;
			}
	}
	private void closeApp(String taskName){
		   		   
		   try {
				String line;
				Process p = Runtime.getRuntime().exec
						(System.getenv("windir") +"\\system32\\"+"tasklist.exe /fo csv /nh");
				BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((line = input.readLine()) != null) {
					//System.out.println(line);
					if(line.contains(taskName)){
						System.out.println(line);
						System.out.println("Kapatiliyor.");
						String[] dizi = line.split(",");
						String temp = dizi[1].substring(1, dizi[1].length()-1);
						int pid = Integer.parseInt(temp);
						System.out.println("pid : " + pid);
						Process k = Runtime.getRuntime().exec ("taskkill /pid " + pid);
					}
				}
				input.close();
			} catch (Exception err) {
				err.printStackTrace();
			}
	   }

}