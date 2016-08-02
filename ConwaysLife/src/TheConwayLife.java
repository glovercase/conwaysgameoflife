import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


@SuppressWarnings("serial")
public class TheConwayLife extends JFrame{
	
	private ConwayWorld conwayWorld = new ConwayWorld();
	
	public TheConwayLife(){
		super("Conway of Life");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		add(conwayWorld, BorderLayout.CENTER);
		
		createMenu();
		pack();
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	private void createMenu() {
		JMenuBar menu = new JMenuBar();
		
		final JMenuItem start = new JMenuItem("Start");
		final JMenuItem reset = new JMenuItem("Reset");
		JMenuItem quit = new JMenuItem("Quit");
		
		menu.add(start);
		menu.add(reset);
		menu.add(quit);
		setJMenuBar(menu);
		conwayWorld.repaint();
		//add listeners to the menu items
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	
				conwayWorld.start();
				
			}
		});
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				conwayWorld.reset();
			}
		});
		quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		
	}
	
	public static void main(String[] args){
		new TheConwayLife();
	}

}
