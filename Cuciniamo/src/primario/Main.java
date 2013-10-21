package primario;
import visual.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//System.out.println("Default Charset=" + Charset.defaultCharset());
		
		// -> Solo Metal e Nimbus installati... Meglio niente... <-

		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
	 	 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		/*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CuciniamoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CuciniamoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CuciniamoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CuciniamoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
		//</editor-fold>

		String os = System.getProperty("os.name").toLowerCase();

		if (os.indexOf( "mac" ) >= 0) {
			//System.out.println("Think different");
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Cuciniamo");
			//System.setProperty("com.apple.mrj.application.apple.menu.about.icon", "visual/img/open.png");
		}
		else if (os.indexOf( "win" ) >= 0)
			; //System.out.println("Where do you want to go today?");
		else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0)
			; //System.out.println( "Where do you want to go tomorrow?" );
		else if (os.indexOf( "bsd" ) >= 0)
			; //System.out.println("Are you guys coming, or what?");
		else
			; //System.out.println("You are nothing to me.");        


		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new CuciniamoJFrame().setVisible(true);
				System.out.println("cazzo");
			}
		});


	}

}
