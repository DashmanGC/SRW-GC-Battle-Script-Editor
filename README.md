SRW GC Battle Script Editor by Dashman
------------------------------------------

USING THIS
-----------

Using this program is pretty straightforward. First of all, make sure you have Java installed in your computer.

To launch the program, simply double-click on it.

This application allows you to edit the contents of BIN files extracted from bpilot.pak (using the BIN Splitter). Go to File -> Open... and browse for the file you want to edit.

Once you load the file, you'll be able to navigate through the "conversations" inside the file. A conversation can be one or many battle lines, displayed as small windows inside the program.

The only think you have to edit in these lines is in the rightmost textarea of the windows (under "Edited"). That's the text that will be saved in the end.

Use the "Recalculate" button in each window to check if the lines you're using are too long. (this will be changed once VWF is implemented)

If you want to turn ASCII text to SJIS (for example, after pasting it from a text file), use the option under the Tools menu. This will convert all edited text inside the current conversation to SJIS.

To save the changes, go to File -> Save As... option.


*UPDATE for v1.3*

Now the program has a "Navigate" menu with shortcuts for moving through the different messages. Beware, the hotkeys won't work if you're focused on one of the text areas (original or edit).

More importantly, in the "File" menu you have now "Import from TXT" and "Export to TXT" options.

"Export" writes all the unique occurrences of battle quotes along with their *first* existing edit to a text file of your choice.

"Import" takes a file with the format of an exported txt, gathering all original-edit pairs in it. Then it applies the corresponding edits in the txt file to all the originals in the current bin file that match the originals in the txt file. For example, in your txt file you have the following:

「遅い！」

Too slow!

So, if you import this file to your bin file, all quotes whose original text is "「遅い！」" will have their edited text set to "Too slow!".

This function exists for 2 reasons:

1) There's many entries where you have repeated quotes.

2) You can export quotes from different SRW games and import them to your character, if those quotes are the same.


*UPDATE for v1.3b*

There's now an "import simplified" function that does the same as the normal import, but ignores punctuation and small hiragana characters for the original quote.

This means that if your text file has:

「この！」

Bastard!

And the bin file has entries for 「この！」, 「この…」 and 「このっ！」, they all will get the translation "Bastard!".

This function exists to facilitate importation of quotes from other SRW games, since the differences are usually in punctuation. Use along with the other import function for better results.


OTHER STUFF
------------

A typical attack conversation has one "NO ACTION" line and one "ACTION" line. Lines labeled as "NO ACTION" are used when the mech is standing still, whereas "ACTION" lines are said in the middle of an attack (shooting, for example). Other lines are typically one "NO ACTION" line. Of course, numbers will vary.

The program automatically tells you which character is saying which line. Keep in mind that, even though a file is dedicated to one specific pilot, the conversations can have several people speaking (which is why I call them "conversations" in the first place). For example, Gou's lines usually involve Shou berating him and Gai trying to calm them down.

You can use the ID and portrait numbers to locate the portrait used during the dialogue if you extracted the SP files from bpilot.pak (using the SP Extractor). For example, if the ID is 123 and the portrait is 456, the BMP files showing the portrait will be p123456.sp_x.bmp (where x is a number from 1 to 4). If a pilot has several outfits (like Amuro), there will be lines repeated for each outfit set where the only difference is the portrait.

* I'll use this chance to say that the Dragonar boys (Kaine, Tapp and Light) have errors in some of their portraits. During their introduction stage (4?), you can see them wearing their pilot suits occasionally during battle, even though they're supposed to be seen with their student clothes during the whole scenario. That's an error of the game. This tool doesn't alter portraits in any way (unless I messed up) and won't do it in the future, since there's still some uncertainty about when exactly does a battle line trigger.

The Voice value is simply informative. Some lines don't have any voices, such as the ones for Kouji when he's unconscious or some for the Nubia cultists.

And that's pretty much it. Have fun.