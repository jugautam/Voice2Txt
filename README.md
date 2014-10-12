Audio2Txt
==========
Through using AUDIO CAPTCHAS in different websites, we want to create a big Database where a TEXT WORD (The one typed by the captcha user) is linked to a fingerprint of an AUDIO FILE (The one played on the captcha). But Why? .....We want to create subtitles for the media, by matching the audio word's fingerprints from a video, to the fingerprints in our Database, so we will be able to display the subtitles in Real Time or with minimal latency. By having human contribution in this process, we factor in the accent and tones with which words are spoken. 

With this, we totally automate the currently subtitling work process, we also increase the accuracy of the subtitles and we open the door for further translation of the subtitle(once is completed) to another language. It's a WIN-WIN business model, in which people contribute to the subtitling and we make the subtitle possible. We would also OpenSource the database to create an ecosystem for other applications to be built using it.

Dependendancies
===================
1. NodeJS
2. MeteorJS
3. fpcalc command-line tool 

Way it works
=======

+ Sample generation through Audio Captcha
  1. Short audio phrases with varying accent and tones are used
  2. User inputs deductions in terms of text are stored against the audio source
  3. fpcalc package is used to generate a fingerprint for the audio clip
  4. The fingerprint & text are saved in the database
  
+ Subtitle Generation for an uploaded video sample
  1. The audio is stripped from the video saple uploaded
  2. A fingerprint for the audio sample is generated
  3. This fingerprint is searched for a match from the sample database
  4. If its a match, then the text is returned back and a subtitle is constructed.
  5. The subtitle which is in the form of a text is translated into other languages through machine translation
  
