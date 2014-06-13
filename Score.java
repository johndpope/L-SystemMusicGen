import org.jfugue.Pattern;

/**
 * Handles musical details; stores the music score.
 * 
 * @author Harry Allen
 */
public class Score
{
	private Pattern			score;
	private int				keySig;															//Stores current key signature
	private int				tempo;															//Stores tempo for music to be played
	private double			degree;															//Represents the degree of the current pitch in the current key signature
	private int				note;
	private int				prevNote;
	private int 			voices;															//Represents the number of voices currently active in the score
	private int 			layers;															//Represents the number of layers currently active in a voice
	private int				beat;
	private static String[]	keySigs	= 														//Stores key signatures
		{ "C", "G", "D", "A", "E", "B", "Gb/F#", "Db", "Ab", "Eb", "Bb", "F" };
	private static String[]	notes	= 														//Stores all possible notes
		{ "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B" };
	
	/**
	 * Default Constructor.
	 */
	public Score()
	{
		keySig = 1;
		tempo = 120;
		degree = 1;
		note = -1;
		prevNote = -1;
		voices = 1;
		layers = 1;
		beat = 1;
	}
	
	/**
	 * @return Value of the key signature
	 */
	public String getKey()
	{
		return keySigs[this.keySig - 1];
	}
	
	public int getKeyInt()
	{
		return keySig;
	}

	/**
	 * @return Returns value of tempo
	 */
	public int getTempo()
	{
		return this.tempo;
	}
	
	/**
	 * @return The number of voices currently present
	 */
	public int getVoices()
	{
		return voices;
	}
	
	/**
	 * @return The number of layers currently present
	 */
	public int getLayers()
	{
		return layers;
	}
	
	/**
	 * @return The current beat of the measure
	 */
	public int getBeat()
	{
		return beat;
	}
	
	/**
	 * @return The integer representation of the current note
	 */
	public int getNote()
	{
		return note;
	}
	
	/**
	 * @param note  integer representation of the note to be returned
	 * @return The string representation of the note passed as parameter
	 */
	public String getNoteStr(int note)
	{
		return notes[note];
	}
	
	/**
	 * @return The integer representation of the previous note
	 */
	public int getPrevNote()
	{
		return prevNote;
	}
	
	/**
	 * @return The degree of the current note
	 */
	public double getDegree()
	{
		return degree;
	}
	
	/**
	 * @return The generated score
	 */
	public Pattern getScore()
	{
		return score;
	}

	/**
	 * Sets the value of keySig and the key of the analyzer to the integer accepted. This forces the analyzer to reset and analyze MIDI files in the
	 * new key.
	 * 
	 * @param key the new key signature
	 */
	public void setKey(int key)
	{
		if (key >= 1 && key <= 12)
		{
			if (key != this.keySig)
				this.keySig = key;
		}
		
		else
			this.keySig = 1;
	}

	/**
	 * Sets the value of tempo to the integer accepted.
	 * 
	 * @param tempo the new tempo
	 */
	public void setTempo(int tempo)
	{
		this.tempo = tempo;
	}
	
	/**
	 * Sets the number of voices present to the parameter.
	 * 
	 * @param newvoices  new number of voices
	 */
	public void setVoices(int newvoices)
	{
		if(newvoices > 0 && newvoices <= 16)
			voices = newvoices;
	}
	
	/**
	 * Sets the number of layers present to the parameter.
	 * 
	 * @param newlayers  new number of layers
	 */
	public void setLayers(int newlayers)
	{
		if(newlayers > 0 && newlayers <= 16)
			layers = newlayers;
	}
	
	/**
	 * Sets the current note to the parameter and the previous note is recorded as prevNote.
	 * Scale degree is recorded, with accidentals represented as numbers between integer scale degrees.
	 * 
	 * @param newnote  next note recorded
	 */
	public void setNote(int newnote)
	{
		prevNote = note;
		note = newnote;
		degree = 1.0;
		int tonic = 0;
		
		while(notes[tonic] != keySigs[keySig])
			tonic = upHalfStep(tonic);
		
		while(tonic != note)
		{
			if(degree == 1.0)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
			
			else if(degree == 1.5)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
				
			else if(degree == 2.0)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
			
			else if(degree == 2.5)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
				
			else if(degree == 3.0)
			{
				tonic = upHalfStep(tonic);
				degree += 1.0;
			}
				
			else if(degree == 4.0)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
			
			else if(degree == 4.5)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
				
			else if(degree == 5.0)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
			
			else if(degree == 5.5)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
				
			else if(degree == 6.0)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
			
			else if(degree == 6.5)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
				
			else if(degree == 7.0)
			{
				tonic = upHalfStep(tonic);
				degree += 1.0;
			}
			
			else
			{
				tonic = upHalfStep(tonic);
				degree = 1.5;
			}
			
			if(tonic == 12)
				tonic = 0;
		}
		
		if(tonic == 12)
			tonic = 0;
		
		if(degree == 8.0)
			degree = 1.0;
	}

	/**
	 * Sets the current note to the parameter and the previous note is recorded as prevNote.
	 * Scale degree is recorded, with accidentals represented as numbers between integer scale degrees.
	 * 
	 * @param pitch  the pitch of the note to be set
	 */
	public void setNotePitch(int pitch)
	{
		int tonic = 0;
		pitch = pitch % 12;
		prevNote = note;
		degree = 1;
		
		switch (pitch)
		{
			case 0:
				note = 0;
				break;
				
			case 1:
				note = 1;
				break;

			case 2:
				note = 2;
				break;

			case 3:
				note = 3;
				break;

			case 4:
				note = 4;
				break;

			case 5:
				note = 5;
				break;

			case 6:
				note = 6;
				break;

			case 7:
				note = 7;
				break;

			case 8:
				note = 8;
				break;

			case 9:
				note = 9;
				break;

			case 10:
				note = 10;
				break;

			case 11:
				note = 11;
				break;

			default:
				note = 0;
				break;
		}
		
		while(!notes[tonic].equals(keySigs[keySig-1]))
			tonic = upHalfStep(tonic);
		
		while(tonic != pitch)
		{
			if(degree == 1.0)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
			
			else if(degree == 1.5)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
				
			else if(degree == 2.0)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
			
			else if(degree == 2.5)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
				
			else if(degree == 3.0)
			{
				tonic = upHalfStep(tonic);
				degree += 1.0;
			}
				
			else if(degree == 4.0)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
			
			else if(degree == 4.5)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
				
			else if(degree == 5.0)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
			
			else if(degree == 5.5)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
				
			else if(degree == 6.0)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
			
			else if(degree == 6.5)
			{
				tonic = upHalfStep(tonic);
				degree += 0.5;
			}
				
			else if(degree == 7.0)
			{
				tonic = upHalfStep(tonic);
				degree += 1.0;
			}
			
			else
			{
				tonic = upHalfStep(tonic);
				degree = 1.5;
			}
			
			if(tonic == 12)
				tonic = 0;
		}
		
		if(tonic == 12)
			tonic = 0;
		
		if(degree == 8.0)
			degree = 1.0;
	}
	
	/**
	 * Set score to the parameter.
	 * 
	 * @param pattern  new score
	 */
	public void setScore(Pattern pattern)
	{
		score = pattern;
	}
	
	/**
	 * Appends a voice to the score.
	 * 
	 * @param newvoice  voice to be appended
	 */
	public void appendVoice(String newvoice)
	{
		score = new Pattern(score.toString() + " V1 I87 " + newvoice + " ");
	}
	
	/**
	 * Resets the score in preparation for new music.
	 */
	public void resetScore()
	{
		voices = 1;
		degree = 1;
		layers = 1;
		beat = 1;
		note = -1;
		prevNote = -1;
	}
	
	/**
	 * Increments the beat.
	 */
	public void nextBeat()
	{
		if(beat < 4)
			++beat;
		
		else
			beat = 1;
	}

	/**
	 * Accepts an integer representing a note value, and increments it a half step.
	 * 
	 * @param note the note to be modified
	 * @return The modified note.
	 */
	public int upHalfStep(int note)
	{
		return note + 1;
	}

	/**
	 * Accepts an integer representing a note value, and decrements it a half step.
	 * 
	 * @param note the note to be modified
	 * @return The modified note.
	 */
	public int downHalfStep(int note)
	{
		return note - 1;
	}

	/**
	 * Accepts an integer representing a note value, and increments it a whole step.
	 * 
	 * @param note the note to be modified
	 * @return The modified note.
	 */
	public int upWholeStep(int note)
	{
		return note + 2;
	}

	/**
	 * Accepts an integer representing a note value, and decrements it a whole step.
	 * 
	 * @param note the note to be modified
	 * @return The modified note.
	 */
	public int downWholeStep(int note)
	{
		return note - 2;
	}

	/**
	 * Accepts an integer representing a note value, and takes it up an octave.
	 * 
	 * @param note the note to be modified
	 * @return The modified note
	 */
	public int upOctave(int note)
	{
		return note + 12;
	}

	/**
	 * Accepts an integer representing a note value, and decrements it an octave.
	 * 
	 * @param note the note to be modified
	 * @return The modified note.
	 */
	public int downOctave(int note)
	{
		return note - 12;
	}
}