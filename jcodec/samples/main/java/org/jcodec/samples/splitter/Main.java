package org.jcodec.samples.splitter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

import org.apache.commons.io.FileUtils;
import org.jcodec.codecs.prores.ProresDecoder;
import org.jcodec.common.io.FileRAInputStream;
import org.jcodec.common.model.RationalLarge;
import org.jcodec.containers.mp4.MP4Demuxer;
import org.jcodec.containers.mp4.MP4Demuxer.DemuxerTrack;
import org.jcodec.player.Player;
import org.jcodec.player.Stepper;
import org.jcodec.player.filters.JCodecPacketSource;
import org.jcodec.player.filters.JCodecVideoSource;
import org.jcodec.player.filters.JSoundAudioOut;
import org.jcodec.player.filters.PacketSource;
import org.jcodec.player.filters.audio.AudioMixer;
import org.jcodec.player.filters.audio.AudioSource;
import org.jcodec.player.filters.audio.JCodecAudioSource;
import org.jcodec.player.filters.audio.ToneAudioSource;
import org.jcodec.player.filters.audio.AudioMixer.Pin;
import org.jcodec.player.filters.http.HttpMedia;
import org.jcodec.player.filters.http.HttpPacketSource;
import org.jcodec.player.ui.SwingVO;

public class Main  implements KeyListener {

	  private AudioMixer mixer;
	private Player player;

	public void keyTyped(KeyEvent e) {
	    }

	    public void keyReleased(KeyEvent e) {
	    }

	    public void keyPressed(KeyEvent e) {
	        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
	            if (player.getStatus() == Player.Status.PAUSED) {
	                if (stepper != null) {
	                    player.seek(stepper.getPos());
	                    stepper = null;
	                }
	                player.play();
	            } else
	                player.pause();
	        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	            RationalLarge pos = player.getPos();
	            player.seek(new RationalLarge(pos.getNum() - pos.getDen() * 15, pos.getDen()));
	        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
	            RationalLarge pos = player.getPos();
	            player.seek(new RationalLarge(pos.getNum() + pos.getDen() * 15, pos.getDen()));
	        } 
	        else if (e.getKeyCode() == KeyEvent.VK_D) {
	        	
	        	ProresDecoder.ScalinfValue=1;
	        }
	        else if (e.getKeyCode() == KeyEvent.VK_F) {
	        	
	        	ProresDecoder.ScalinfValue=2;
	        }
	        else if (e.getKeyCode() == KeyEvent.VK_G) {
	        	
	        	ProresDecoder.ScalinfValue=4;
	        }
	        else if (e.getKeyCode() == KeyEvent.VK_H) {
	        	
	        	ProresDecoder.ScalinfValue=8;
	        }
	        else if (e.getKeyCode() == KeyEvent.VK_J) {
	        	
	        	ProresDecoder.ScalinfValue=16;
	        }
	        else if (e.getKeyCode() == KeyEvent.VK_K) {
	        	
	        	ProresDecoder.ScalinfValue=32;
	        }
	        else if (e.getKeyCode() == KeyEvent.VK_M) {
	        	
	        	ProresDecoder.ScalinfValue=64;
	        }

	        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	        	
	        	ProresDecoder.ScalinfValue=32;
	            // if (player.getStatus() != Player.Status.PAUSED) {
	            // player.pause();
	            // return;
	            // }
	            //
	            // try {
	            // if (stepper == null) {
	            // stepper = new Stepper(video, mixer, vo, new JSoundAudioOut());
	            // stepper.setListeners(player.getListeners());
	            // stepper.gotoFrame(player.getFrameNo());
	            // }
	            // stepper.prev();
	            // } catch (IOException e1) {
	            // System.out.println("Couldn't step " + e1.getMessage());
	            // }
	        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	        	
	            if (player.getStatus() != Player.Status.PAUSED) {
	                player.pause();
	                return;
	            }
	            try {
	                if (stepper == null) {
	                    stepper = new Stepper(video, mixer, vo, new JSoundAudioOut());
	                    stepper.setListeners(player.getListeners());
	                    stepper.gotoFrame(player.getFrameNo());
	                }
	                stepper.next();
	            } catch (IOException e1) {
	                System.out.println("Couldn't step " + e1.getMessage());
	            }
	        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	            player.destroy();
	            System.exit(-1);
	        } else if (e.getKeyChar() >= '0' && e.getKeyChar() < '9') {
	            int ch = e.getKeyChar() - '0';
	            for (Pin pin : mixer.getPins()) {
	                if (ch < pin.getLabels().length) {
	                    pin.toggle(ch);
	                    break;
	                } else
	                    ch -= pin.getLabels().length;
	            }
	        }
	    }
	
	    public static void main(String[] args) throws IOException {
	    	new Main().run();
	    }
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public void run() throws IOException {
		MP4Demuxer demuxer1 = new MP4Demuxer(new FileRAInputStream(new File("simps.mp4")));
		DemuxerTrack vt1 = demuxer1.getVideoTrack();
		System.err.println(vt1.getDuration().getNum());
		
		JFrame frame = new JFrame("Player");

		 vo = new SwingVO();
        frame.getContentPane().add(vo, BorderLayout.CENTER);

        // Finish setting up the frame, and show it.
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        vo.setVisible(true);

        
        JCodecPacketSource pack = new JCodecPacketSource(new File("/tmp/test.mov"));
         video=  new JCodecVideoSource(pack.getVideo());
        

        List<? extends PacketSource> audioTracks = pack.getAudio();
        System.err.println(audioTracks.size());
        AudioSource[] audio = new AudioSource[audioTracks.size()];
        for (int i = 0; i < audioTracks.size(); i++) {
            audio[i] = new JCodecAudioSource(audioTracks.get(i));
        }
        mixer = new AudioMixer(2, new ToneAudioSource());

        player = new Player(video, mixer, vo, new JSoundAudioOut());

        frame.addKeyListener(this);

        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(1900, 1060));

        player.play();
        
		
	}
	  private Stepper stepper;
	  SwingVO vo;
	  JCodecVideoSource video;
}
