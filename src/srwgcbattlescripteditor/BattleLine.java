/*
 * Copyright (C) 2014 Dashman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package srwgcbattlescripteditor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonatan
 */
public class BattleLine {
        int charID;
        int portrait;
        int voice;
        int status;
        String originalText;
        String editText;
        byte[] extra;
        
        String font_encoding = "MS932";

        public BattleLine(){
            charID = 0;
            portrait = 0;
            voice = 0;
            status = 1;
            originalText = "";
            editText = "";
            extra = null;
        }

        // Return the battle line as a byte array with the format used in the BIN files.
        // Special endings for lines are not included, however, the last byte will always be 0a
        // (used for carriage returns), which is the first byte of every line's ending.
        // The external routine that calls this program will have to add the rest of the bytes
        // that form the special endings (0a 00 for the end of a "conversation" or 00 for the end of the file).
        // Likewise, the first byte that is used in the first battle line (also 0a) is not included here
        // and it rests on the external routine to handle as well.
        public byte[] getBytes(){
            byte[] result = null;

            // Determine the size of the byte arrays
            byte[] beginning = new byte[24];    // Up to the character ID, before the extra part

            byte[] char_section = new byte[4];

            String[] lines = editText.split("\n");

            byte[] separator = new byte[1];
            separator[0] = 0x0a;

            // The voice section might not be present
            byte[] voice_section = null;
            if (voice != -1)
                voice_section = new byte[10];

            // Write the data
            beginning[0] = (byte) (0x81 & 0xff);
            beginning[1] = (byte) (0x95 & 0xff);
            beginning[2] = (byte) (0x82 & 0xff);
            beginning[3] = (byte) (0x86 & 0xff);

            beginning[6] = 4;

            beginning[8] = (byte) (portrait & 0xff);
            beginning[9] = (byte) ( (portrait >> 8) & 0xff);
            beginning[10] = (byte) ( (portrait >> 16) & 0xff);
            beginning[11] = (byte) ( (portrait >> 24) & 0xff);

            beginning[20] = (byte) (status & 0xff);

            char_section[0] = (byte) (0x81 & 0xff);
            char_section[1] = (byte) (0x93 & 0xff);

            char_section[2] = (byte) (charID & 0xff);
            char_section[3] = (byte) ( (charID >> 8) & 0xff);

            if (voice_section != null){
                voice_section[0] = (byte) (0x81 & 0xff);
                voice_section[1] = (byte) (0x95 & 0xff);
                voice_section[2] = (byte) (0x82 & 0xff);
                voice_section[3] = (byte) (0x96 & 0xff);

                voice_section[4] = (byte) (voice & 0xff);
                voice_section[5] = (byte) ( (voice >> 8) & 0xff);
                voice_section[6] = (byte) ( (voice >> 16) & 0xff);
                voice_section[7] = (byte) ( (voice >> 24) & 0xff);
            }

            // Join everything
            ByteArrayOutputStream os = new ByteArrayOutputStream( );
            try {
                os.write(beginning);

                if (extra != null)
                    os.write(extra);

                os.write(char_section);

                if (voice_section != null)
                    os.write(voice_section);

                for (int i = 0; i < lines.length; i++){
                    os.write(lines[i].getBytes(font_encoding));
                    os.write(separator);
                }

                result = os.toByteArray();
            } catch (IOException ex) {
                Logger.getLogger(BattleLine.class.getName()).log(Level.SEVERE, null, ex);
            }

            return result;
        }
}
