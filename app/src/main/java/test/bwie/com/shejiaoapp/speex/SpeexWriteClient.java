package test.bwie.com.shejiaoapp.speex;

import java.io.IOException;

/**
 * A client write tags to local file.
 */
public class SpeexWriteClient {

	//private long timeBase = 0;
	// private int sampleRate = 0;

	// (0=NB, 1=WB and 2=UWB)
	private int mode = 0;

	// 8000; 16000; 32000; 8000;
	protected int sampleRate = 8000;

	/** Defines the number of channels of the audio input (1=mono, 2=stereo). */
	protected int channels = 1;

	/** Defines the number of frames per speex packet. */
	protected int nframes = 1;

	/** Defines whether or not to use VBR (Variable Bit Rate). */
	protected boolean vbr = false;

	OggSpeexWriter speexWriter = null;// new OggSpeexWriter(mode, sampleRate,
										// channels, nframes, vbr);

	public SpeexWriteClient() {

	}

	public void start(String fileName) {

		init(fileName);
	}

	private void init(String fileName) {
		// File file = new File(saveAsFileName);

		// xiaomi
		// Decoding 8000 Hz audio using narrowband mode
		// Bitrate is use: 2150 bps

		mode =0;
		sampleRate=8000;//people
		//sampleRate=11000;//yong
		//sampleRate=13500;//Tom
		//sampleRate=5500;//Bee
		vbr=true;
		
		//调用OGG封装
		speexWriter = new OggSpeexWriter(mode, sampleRate, channels, nframes, vbr);

		try {
			speexWriter.open(fileName);

			speexWriter.writeHeader("Encoded with:bw 1503d online ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		if (speexWriter != null) {
			try {
				speexWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			speexWriter = null;
		}
	}

	public void writeTag(byte[] buf, int size) {
		try {
			speexWriter.writePacket(buf, 0, size);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setSampleRate(int sampleRate) {
		this.sampleRate = sampleRate;
	}

}
