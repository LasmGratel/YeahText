package dev.lasm.yeahtext;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YeahTextTest {
    @Test
    void test() {
        var original = "Hello text string my old friend.";
        assertEquals("\uD835\uDC07\uD835\uDC1E\uD835\uDC25\uD835\uDC25\uD835\uDC28 \uD835\uDC2D\uD835\uDC1E\uD835\uDC31\uD835\uDC2D \uD835\uDC2C\uD835\uDC2D\uD835\uDC2B\uD835\uDC22\uD835\uDC27\uD835\uDC20 \uD835\uDC26\uD835\uDC32 \uD835\uDC28\uD835\uDC25\uD835\uDC1D \uD835\uDC1F\uD835\uDC2B\uD835\uDC22\uD835\uDC1E\uD835\uDC27\uD835\uDC1D.",
            YeahText.transform(original, "bold"));

        assertEquals("\uD835\uDC3B\uD835\uDC52\uD835\uDC59\uD835\uDC59\uD835\uDC5C \uD835\uDC61\uD835\uDC52\uD835\uDC65\uD835\uDC61 \uD835\uDC60\uD835\uDC61\uD835\uDC5F\uD835\uDC56\uD835\uDC5B\uD835\uDC54 \uD835\uDC5A\uD835\uDC66 \uD835\uDC5C\uD835\uDC59\uD835\uDC51 \uD835\uDC53\uD835\uDC5F\uD835\uDC56\uD835\uDC52\uD835\uDC5B\uD835\uDC51.",
            YeahText.transform(original, "italic"));

        assertEquals("\uD835\uDE43\uD835\uDC86\uD835\uDE61\uD835\uDC8D\uD835\uDE64 \uD835\uDE69\uD835\uDC86\uD835\uDE6D\uD835\uDC95 \uD835\uDC94\uD835\uDE69\uD835\uDC93\uD835\uDE5E\uD835\uDC8F\uD835\uDE5C \uD835\uDE62\uD835\uDC9A \uD835\uDC90\uD835\uDE61\uD835\uDC85 \uD835\uDC87\uD835\uDE67\uD835\uDC8A\uD835\uDE5A\uD835\uDC8F\uD835\uDE59.",
            YeahText.transform(original, "bold-alternating-italic"));

        assertEquals("\uD835\uDE0F\uD835\uDC52\uD835\uDE2D\uD835\uDC59\uD835\uDE30 \uD835\uDE35\uD835\uDC52\uD835\uDE39\uD835\uDC61 \uD835\uDC60\uD835\uDE35\uD835\uDC5F\uD835\uDE2A\uD835\uDC5B\uD835\uDE28 \uD835\uDE2E\uD835\uDC66 \uD835\uDC5C\uD835\uDE2D\uD835\uDC51 \uD835\uDC53\uD835\uDE33\uD835\uDC56\uD835\uDE26\uD835\uDC5B\uD835\uDE25.",
            YeahText.transform(original, "italic-switch-serifs"));

        assertEquals("H\uD835\uDC1El\uD835\uDC25o t\uD835\uDC1Ex\uD835\uDC2D \uD835\uDC2Ct\uD835\uDC2Bi\uD835\uDC27g m\uD835\uDC32 \uD835\uDC28l\uD835\uDC1D \uD835\uDC1Fr\uD835\uDC22e\uD835\uDC27d.",
            YeahText.transform(original, "alternating-bold"));

        assertEquals("⧦ⅇǁǁ☉ ╬ⅇ⨳╬ \uD835\uDD64╬ℾⅈℼ\uD80C\uDE5B ⩕ℽ ☉ǁⅆ ⨎ℾⅈⅇℼⅆ.",
            YeahText.transform(original, "weird-double-struck"));

        assertEquals("\uD835\uDE77\uD835\uDE8E\uD835\uDE95\uD835\uDE95\uD835\uDE98 \uD835\uDE9D\uD835\uDE8E\uD835\uDEA1\uD835\uDE9D \uD835\uDE9C\uD835\uDE9D\uD835\uDE9B\uD835\uDE92\uD835\uDE97\uD835\uDE90 \uD835\uDE96\uD835\uDEA2 \uD835\uDE98\uD835\uDE95\uD835\uDE8D \uD835\uDE8F\uD835\uDE9B\uD835\uDE92\uD835\uDE8E\uD835\uDE97\uD835\uDE8D.",
            YeahText.transform(original, "monospace"));

        assertEquals("ℋℯ\uD835\uDCC1\uD835\uDCC1ℴ \uD835\uDCC9ℯ\uD835\uDCCD\uD835\uDCC9 \uD835\uDCC8\uD835\uDCC9\uD835\uDCC7\uD835\uDCBE\uD835\uDCC3ℊ \uD835\uDCC2\uD835\uDCCE ℴ\uD835\uDCC1\uD835\uDCB9 \uD835\uDCBB\uD835\uDCC7\uD835\uDCBEℯ\uD835\uDCC3\uD835\uDCB9.",
            YeahText.transform(original, "script"));

        assertEquals("\uD835\uDCD7ℯ\uD835\uDCF5\uD835\uDCC1\uD835\uDCF8 \uD835\uDCFDℯ\uD835\uDD01\uD835\uDCC9 \uD835\uDCC8\uD835\uDCFD\uD835\uDCC7\uD835\uDCF2\uD835\uDCC3\uD835\uDCF0 \uD835\uDCF6\uD835\uDCCE ℴ\uD835\uDCF5\uD835\uDCB9 \uD835\uDCBB\uD835\uDCFB\uD835\uDCBE\uD835\uDCEE\uD835\uDCC3\uD835\uDCED.",
            YeahText.transform(original, "alt-cursive"));

        assertEquals("H̳e̳l̳l̳o̳ ̳t̳e̳x̳t̳ ̳s̳t̳r̳i̳n̳g̳ ̳m̳y̳ ̳o̳l̳d̳ ̳f̳r̳i̳e̳n̳d̳.̳",
            YeahText.transform(original, "double-underline"));

        assertEquals("\uD835\uDC6F\uD835\uDC86\uD835\uDC8D\uD835\uDC8D\uD835\uDC90 \uD835\uDC95\uD835\uDC86\uD835\uDC99\uD835\uDC95 \uD835\uDC94\uD835\uDC95\uD835\uDC93\uD835\uDC8A\uD835\uDC8F\uD835\uDC88 \uD835\uDC8E\uD835\uDC9A \uD835\uDC90\uD835\uDC8D\uD835\uDC85 \uD835\uDC87\uD835\uDC93\uD835\uDC8A\uD835\uDC86\uD835\uDC8F\uD835\uDC85.",
            YeahText.transform(original, "bold-italic"));
        assertEquals("\uD835\uDDDB\uD835\uDDF2\uD835\uDDF9\uD835\uDDF9\uD835\uDDFC \uD835\uDE01\uD835\uDDF2\uD835\uDE05\uD835\uDE01 \uD835\uDE00\uD835\uDE01\uD835\uDDFF\uD835\uDDF6\uD835\uDDFB\uD835\uDDF4 \uD835\uDDFA\uD835\uDE06 \uD835\uDDFC\uD835\uDDF9\uD835\uDDF1 \uD835\uDDF3\uD835\uDDFF\uD835\uDDF6\uD835\uDDF2\uD835\uDDFB\uD835\uDDF1.",
            YeahText.transform(original, "bold-sans"));
        assertEquals("\uD835\uDE0F\uD835\uDE26\uD835\uDE2D\uD835\uDE2D\uD835\uDE30 \uD835\uDE35\uD835\uDE26\uD835\uDE39\uD835\uDE35 \uD835\uDE34\uD835\uDE35\uD835\uDE33\uD835\uDE2A\uD835\uDE2F\uD835\uDE28 \uD835\uDE2E\uD835\uDE3A \uD835\uDE30\uD835\uDE2D\uD835\uDE25 \uD835\uDE27\uD835\uDE33\uD835\uDE2A\uD835\uDE26\uD835\uDE2F\uD835\uDE25.",
            YeahText.transform(original, "italic-sans"));
        assertEquals("\uD835\uDE43\uD835\uDE5A\uD835\uDE61\uD835\uDE61\uD835\uDE64 \uD835\uDE69\uD835\uDE5A\uD835\uDE6D\uD835\uDE69 \uD835\uDE68\uD835\uDE69\uD835\uDE67\uD835\uDE5E\uD835\uDE63\uD835\uDE5C \uD835\uDE62\uD835\uDE6E \uD835\uDE64\uD835\uDE61\uD835\uDE59 \uD835\uDE5B\uD835\uDE67\uD835\uDE5E\uD835\uDE5A\uD835\uDE63\uD835\uDE59.",
            YeahText.transform(original, "bold-italic-sans"));
        assertEquals("H\uD835\uDC52l\uD835\uDC59o t\uD835\uDC52x\uD835\uDC61 \uD835\uDC60t\uD835\uDC5Fi\uD835\uDC5Bg m\uD835\uDC66 \uD835\uDC5Cl\uD835\uDC51 \uD835\uDC53r\uD835\uDC56e\uD835\uDC5Bd.",
            YeahText.transform(original, "alternating-italic"));
        assertEquals("ℍ\uD835\uDD56\uD835\uDD5D\uD835\uDD5D\uD835\uDD60 \uD835\uDD65\uD835\uDD56\uD835\uDD69\uD835\uDD65 \uD835\uDD64\uD835\uDD65\uD835\uDD63\uD835\uDD5A\uD835\uDD5F\uD835\uDD58 \uD835\uDD5E\uD835\uDD6A \uD835\uDD60\uD835\uDD5D\uD835\uDD55 \uD835\uDD57\uD835\uDD63\uD835\uDD5A\uD835\uDD56\uD835\uDD5F\uD835\uDD55.",
            YeahText.transform(original, "double-struck"));
        assertEquals("⧦\uD835\uDD56ǁ\uD835\uDD5D☉ ╬\uD835\uDD56⨳\uD835\uDD65 \uD835\uDD64╬\uD835\uDD63ⅈ\uD835\uDD5F\uD80C\uDE5B ⩕\uD835\uDD6A \uD835\uDD60ǁ\uD835\uDD55 \uD835\uDD57ℾ\uD835\uDD5Aⅇ\uD835\uDD5Fⅆ.",
            YeahText.transform(original, "alternating-weird-double-struck"));
        assertEquals("\uD835\uDDA7\uD835\uDDBE\uD835\uDDC5\uD835\uDDC5\uD835\uDDC8 \uD835\uDDCD\uD835\uDDBE\uD835\uDDD1\uD835\uDDCD \uD835\uDDCC\uD835\uDDCD\uD835\uDDCB\uD835\uDDC2\uD835\uDDC7\uD835\uDDC0 \uD835\uDDC6\uD835\uDDD2 \uD835\uDDC8\uD835\uDDC5\uD835\uDDBD \uD835\uDDBF\uD835\uDDCB\uD835\uDDC2\uD835\uDDBE\uD835\uDDC7\uD835\uDDBD.",
            YeahText.transform(original, "sans-serif"));
        assertEquals("\uD835\uDCD7\uD835\uDCEE\uD835\uDCF5\uD835\uDCF5\uD835\uDCF8 \uD835\uDCFD\uD835\uDCEE\uD835\uDD01\uD835\uDCFD \uD835\uDCFC\uD835\uDCFD\uD835\uDCFB\uD835\uDCF2\uD835\uDCF7\uD835\uDCF0 \uD835\uDCF6\uD835\uDD02 \uD835\uDCF8\uD835\uDCF5\uD835\uDCED \uD835\uDCEF\uD835\uDCFB\uD835\uDCF2\uD835\uDCEE\uD835\uDCF7\uD835\uDCED.",
            YeahText.transform(original, "bold-script"));
        assertEquals("ℌ\uD835\uDD22\uD835\uDD29\uD835\uDD29\uD835\uDD2C \uD835\uDD31\uD835\uDD22\uD835\uDD35\uD835\uDD31 \uD835\uDD30\uD835\uDD31\uD835\uDD2F\uD835\uDD26\uD835\uDD2B\uD835\uDD24 \uD835\uDD2A\uD835\uDD36 \uD835\uDD2C\uD835\uDD29\uD835\uDD21 \uD835\uDD23\uD835\uDD2F\uD835\uDD26\uD835\uDD22\uD835\uDD2B\uD835\uDD21.",
            YeahText.transform(original, "fraktur"));
        assertEquals("\uD835\uDD73\uD835\uDD8A\uD835\uDD91\uD835\uDD91\uD835\uDD94 \uD835\uDD99\uD835\uDD8A\uD835\uDD9D\uD835\uDD99 \uD835\uDD98\uD835\uDD99\uD835\uDD97\uD835\uDD8E\uD835\uDD93\uD835\uDD8C \uD835\uDD92\uD835\uDD9E \uD835\uDD94\uD835\uDD91\uD835\uDD89 \uD835\uDD8B\uD835\uDD97\uD835\uDD8E\uD835\uDD8A\uD835\uDD93\uD835\uDD89.",
            YeahText.transform(original, "bold-fraktur"));
        assertEquals("\uD835\uDD73\uD835\uDD22\uD835\uDD91\uD835\uDD29\uD835\uDD94 \uD835\uDD99\uD835\uDD22\uD835\uDD9D\uD835\uDD31 \uD835\uDD30\uD835\uDD99\uD835\uDD2F\uD835\uDD8E\uD835\uDD2B\uD835\uDD8C \uD835\uDD92\uD835\uDD36 \uD835\uDD2C\uD835\uDD91\uD835\uDD21 \uD835\uDD23\uD835\uDD97\uD835\uDD26\uD835\uDD8A\uD835\uDD2B\uD835\uDD89.",
            YeahText.transform(original, "alt-fraktur"));
        assertEquals("Ɦẻꞎꞎơ ťẻx̂ť śťɼỉήɠ ḿƴ ơꞎɗ ḟɼỉẻήɗ.",
            YeahText.transform(original, "squiggles-top"));
        assertEquals("Ɦ̡ᶒᶅᶅǫ ƫᶒᶍƫ ᶊƫᶉᶖᶇᶃ ᶆƴ ǫᶅᶁ ᶂᶉᶖᶒᶇᶁ.",
            YeahText.transform(original, "squiggles-bottom"));
        assertEquals("Ɦ̡ẻᶅꞎǫ ƫẻᶍť śƫɼᶖήᶃ ᶆƴ ơᶅɗ ḟᶉỉᶒήᶁ.",
            YeahText.transform(original, "alt-squiggles"));
        assertEquals("H̵e̵l̵l̵o̵ ̵t̵e̵x̵t̵ ̵s̵t̵r̵i̵n̵g̵ ̵m̵y̵ ̵o̵l̵d̵ ̵f̵r̵i̵e̵n̵d̵.̵",
            YeahText.transform(original, "short-strikethrough"));
        assertEquals("H̶e̶l̶l̶o̶ ̶t̶e̶x̶t̶ ̶s̶t̶r̶i̶n̶g̶ ̶m̶y̶ ̶o̶l̶d̶ ̶f̶r̶i̶e̶n̶d̶.̶",
            YeahText.transform(original, "long-strikethrough"));
        assertEquals("H͟e͟l͟l͟o͟ ͟t͟e͟x͟t͟ ͟s͟t͟r͟i͟n͟g͟ ͟m͟y͟ ͟o͟l͟d͟ ͟f͟r͟i͟e͟n͟d͟.͟",
            YeahText.transform(original, "underline"));
        assertEquals("H̲e̲l̲l̲o̲ ̲t̲e̲x̲t̲ ̲s̲t̲r̲i̲n̲g̲ ̲m̲y̲ ̲o̲l̲d̲ ̲f̲r̲i̲e̲n̲d̲.̲",
            YeahText.transform(original, "underline-alt"));
        assertEquals("H̳e̳l̳l̳o̳ ̳t̳e̳x̳t̳ ̳s̳t̳r̳i̳n̳g̳ ̳m̳y̳ ̳o̳l̳d̳ ̳f̳r̳i̳e̳n̳d̳.̳",
            YeahText.transform(original, "double-underline"));
        assertEquals("H̷e̷l̷l̷o̷ ̷t̷e̷x̷t̷ ̷s̷t̷r̷i̷n̷g̷ ̷m̷y̷ ̷o̷l̷d̷ ̷f̷r̷i̷e̷n̷d̷.̷",
            YeahText.transform(original, "short-slash"));
        assertEquals("H̸e̸l̸l̸o̸ ̸t̸e̸x̸t̸ ̸s̸t̸r̸i̸n̸g̸ ̸m̸y̸ ̸o̸l̸d̸ ̸f̸r̸i̸e̸n̸d̸.̸",
            YeahText.transform(original, "long-slash"));
        assertEquals("H̴e̴l̴l̴o̴ ̴t̴e̴x̴t̴ ̴s̴t̴r̴i̴n̴g̴ ̴m̴y̴ ̴o̴l̴d̴ ̴f̴r̴i̴e̴n̴d̴.̴",
            YeahText.transform(original, "tilde-strikethrough"));
        assertEquals("˙puǝıɹɟ pןo ʎɯ ƃuıɹʇs ʇxǝʇ oןןǝH",
            YeahText.transform(original, "upside-down"));
        assertEquals("ᎻᎬᏞᏞᎾ ᎢᎬᎲᎢ ᏚᎢᎡᏆᏁᏀ ᎷᎩ ᎾᏞᎠ ᎱᎡᏆᎬᏁᎠ.",
            YeahText.transform(original, "cherokee-large"));
        assertEquals("ꮋꭼꮮꮮꮎ ꭲꭼꮂꭲ ꮪꭲꭱꮖꮑᏽ ꮇꭹ ꮎꮮꭰ ꮁꭱꮖꭼꮑꭰ.",
            YeahText.transform(original, "cherokee-small"));
        assertEquals("Ꮋꭼꮮꮮꮎ ꭲꭼꮂꭲ ꮪꭲꭱꮖꮑᏽ ꮇꭹ ꮎꮮꭰ ꮁꭱꮖꭼꮑꭰ.",
            YeahText.transform(original, "cherokee-titlecase"));
        assertEquals("Ｈｅｌｌｏ ｔｅｘｔ ｓｔｒｉｎｇ ｍｙ ｏｌｄ ｆｒｉｅｎｄ．",
            YeahText.transform(original, "fullwidth"));
        assertEquals("Ｈｅｌｌｏ ｔｅｘｔ ｓｔｒｉｎｇ ｍｙ ｏｌｄ ｆｒｉｅｎｄ．",
            YeahText.transform(original, "vaporwave-ae"));
        assertEquals("Ｈｅｌｌｏ ｔｅｘｔ ｓｔｒｉｎｇ ｍｙ ｏｌｄ ｆｒｉｅｎｄ．",
            YeahText.transform(original, "vaporwave-av"));
        assertEquals("Ｈｅｌｌｏ ｔｅｘｔ ｓｔｒｉｎｇ ｍｙ ｏｌｄ ｆｒｉｅｎｄ．",
            YeahText.transform(original, "vaporwave-eo"));
        assertEquals("Hᴇʟʟᴏ ᴛᴇxᴛ sᴛʀɪɴɢ ᴍʏ ᴏʟᴅ ғʀɪᴇɴᴅ.",
            YeahText.transform(original, "small-caps-comp-f"));
        assertEquals("Hᴇʟʟᴏ ᴛᴇxᴛ sᴛʀɪɴɢ ᴍʏ ᴏʟᴅ ꜰʀɪᴇɴᴅ.",
            YeahText.transform(original, "small-caps"));
        assertEquals("ᴴᵉˡˡᵒ ᵗᵉˣᵗ ˢᵗʳᶦⁿᵍ ᵐʸ ᵒˡᵈ ᶠʳᶦᵉⁿᵈ.",
            YeahText.transform(original, "superscript"));
        assertEquals("Hₑₗₗₒ ₜₑₓₜ ₛₜᵣᵢₙg ₘy ₒₗd fᵣᵢₑₙd.",
            YeahText.transform(original, "subscript"));
        assertEquals("Heͤlloͦ tͭeͤxͯtͭ stͭrͬiͥng mͫy oͦldͩ frͬiͥeͤndͩ.",
            YeahText.transform(original, "mini-me"));
//        assertEquals("tᴴeͤxˡtˡᵒ mˢyͭʳᶦⁿᵍ fͦrˡiͩend.",
//            YeahText.transform(original, "stack"));
        assertEquals("Heͤlloͦ tͭeͤxͯtͭ stͭrͬiͥng mͫy oͦldͩ frͬiͥeͤndͩ.",
            YeahText.transform(original, "mini-superscript"));
        assertEquals("Hᵉₗˡₒ ₜᵉₓᵗ ˢₜʳᵢⁿg ₘʸ ᵒₗᵈ ᶠᵣᶦₑⁿd.",
            YeahText.transform(original, "supersub-1"));
        assertEquals("Hᵉlₗo tₑxᵗ ₛtʳiₙg my ᵒld ᶠrᵢeⁿd.",
            YeahText.transform(original, "supersub-2"));
        assertEquals("Ⓗⓔⓛⓛⓞ ⓣⓔⓧⓣ ⓢⓣⓡⓘⓝⓖ ⓜⓨ ⓞⓛⓓ ⓕⓡⓘⓔⓝⓓ.",
            YeahText.transform(original, "bubble"));
        assertEquals("\uD83C\uDD57\uD83C\uDD54\uD83C\uDD5B\uD83C\uDD5B\uD83C\uDD5E \uD83C\uDD63\uD83C\uDD54\uD83C\uDD67\uD83C\uDD63 \uD83C\uDD62\uD83C\uDD63\uD83C\uDD61\uD83C\uDD58\uD83C\uDD5D\uD83C\uDD56 \uD83C\uDD5C\uD83C\uDD68 \uD83C\uDD5E\uD83C\uDD5B\uD83C\uDD53 \uD83C\uDD55\uD83C\uDD61\uD83C\uDD58\uD83C\uDD54\uD83C\uDD5D\uD83C\uDD53.",
            YeahText.transform(original, "black-bubble"));
        assertEquals("\uD83C\uDD37\uD83C\uDD34\uD83C\uDD3B\uD83C\uDD3B\uD83C\uDD3E \uD83C\uDD43\uD83C\uDD34\uD83C\uDD47\uD83C\uDD43 \uD83C\uDD42\uD83C\uDD43\uD83C\uDD41\uD83C\uDD38\uD83C\uDD3D\uD83C\uDD36 \uD83C\uDD3C\uD83C\uDD48 \uD83C\uDD3E\uD83C\uDD3B\uD83C\uDD33 \uD83C\uDD35\uD83C\uDD41\uD83C\uDD38\uD83C\uDD34\uD83C\uDD3D\uD83C\uDD33.",
            YeahText.transform(original, "square"));
        assertEquals("\uD83C\uDD77\uD83C\uDD74\uD83C\uDD7B\uD83C\uDD7B\uD83C\uDD7E \uD83C\uDD83\uD83C\uDD74\uD83C\uDD87\uD83C\uDD83 \uD83C\uDD82\uD83C\uDD83\uD83C\uDD81\uD83C\uDD78\uD83C\uDD7D\uD83C\uDD76 \uD83C\uDD7C\uD83C\uDD88 \uD83C\uDD7E\uD83C\uDD7B\uD83C\uDD73 \uD83C\uDD75\uD83C\uDD81\uD83C\uDD78\uD83C\uDD74\uD83C\uDD7D\uD83C\uDD73.",
            YeahText.transform(original, "black-square"));
        assertEquals("\uD83C\uDD57ⓔ\uD83C\uDD5Bⓛ\uD83C\uDD5E \uD83C\uDD63ⓔ\uD83C\uDD67ⓣ ⓢ\uD83C\uDD63ⓡ\uD83C\uDD58ⓝ\uD83C\uDD56 \uD83C\uDD5Cⓨ ⓞ\uD83C\uDD5Bⓓ ⓕ\uD83C\uDD61ⓘ\uD83C\uDD54ⓝ\uD83C\uDD53.",
            YeahText.transform(original, "alt-bubble"));
        assertEquals("\uD83C\uDD77\uD83C\uDD34\uD83C\uDD7B\uD83C\uDD3B\uD83C\uDD7E \uD83C\uDD83\uD83C\uDD34\uD83C\uDD87\uD83C\uDD43 \uD83C\uDD42\uD83C\uDD83\uD83C\uDD41\uD83C\uDD78\uD83C\uDD3D\uD83C\uDD76 \uD83C\uDD7C\uD83C\uDD48 \uD83C\uDD3E\uD83C\uDD7B\uD83C\uDD33 \uD83C\uDD35\uD83C\uDD81\uD83C\uDD38\uD83C\uDD74\uD83C\uDD3D\uD83C\uDD73.",
            YeahText.transform(original, "alt-square"));
        assertEquals("Ⓗ\uD83C\uDD34ⓛ\uD83C\uDD3Bⓞ ⓣ\uD83C\uDD34ⓧ\uD83C\uDD43 \uD83C\uDD42ⓣ\uD83C\uDD41ⓘ\uD83C\uDD3Dⓖ ⓜ\uD83C\uDD48 \uD83C\uDD3Eⓛ\uD83C\uDD33 \uD83C\uDD35ⓡ\uD83C\uDD38ⓔ\uD83C\uDD3Dⓓ.",
            YeahText.transform(original, "white-shapes"));
        assertEquals("\uD83C\uDD57\uD83C\uDD74\uD83C\uDD5B\uD83C\uDD7B\uD83C\uDD5E \uD83C\uDD63\uD83C\uDD74\uD83C\uDD67\uD83C\uDD83 \uD83C\uDD82\uD83C\uDD63\uD83C\uDD81\uD83C\uDD58\uD83C\uDD7D\uD83C\uDD56 \uD83C\uDD5C\uD83C\uDD88 \uD83C\uDD7E\uD83C\uDD5B\uD83C\uDD73 \uD83C\uDD75\uD83C\uDD61\uD83C\uDD78\uD83C\uDD54\uD83C\uDD7D\uD83C\uDD53.",
            YeahText.transform(original, "black-shapes"));

        assertEquals("\uD83C\uDD57\uD83C\uDD74\uD83C\uDD3Bⓛ\uD83C\uDD7E ⓣ\uD83C\uDD74\uD83C\uDD87ⓣ \uD83C\uDD42\uD83C\uDD63\uD83C\uDD81ⓘ\uD83C\uDD7Dⓖ \uD83C\uDD7C\uD83C\uDD88 ⓞ\uD83C\uDD3B\uD83C\uDD33 \uD83C\uDD75\uD83C\uDD81\uD83C\uDD38\uD83C\uDD74\uD83C\uDD7D\uD83C\uDD73.",
            YeahText.transform(original, "rand-bubble-square"));
        assertEquals("\uD835\uDC6F\uD835\uDE26\uD835\uDDF9\uD835\uDC25\uD835\uDE64 \uD835\uDC2D\uD835\uDE5A\uD835\uDE6D\uD835\uDC2D \uD835\uDE34\uD835\uDC95\uD835\uDE67\uD835\uDC22\uD835\uDE63\uD835\uDC20 \uD835\uDE2E\uD835\uDE6E \uD835\uDC28\uD835\uDE2D\uD835\uDDF1 \uD835\uDE27\uD835\uDE33\uD835\uDDF6\uD835\uDE5A\uD835\uDE63\uD835\uDE59.",
            YeahText.transform(original, "ransom-subtle"));
        assertEquals("ℌⓔ\uD835\uDDF9\uD835\uDC25\uD83C\uDD3E \uD835\uDC61\uD83C\uDD74\uD83C\uDD87\uD835\uDC95 \uD835\uDE9C\uD835\uDD99\uD83C\uDD81\uD835\uDC22\uD83C\uDD7D\uD835\uDC88 \uD83C\uDD5C\uD83C\uDD68 \uD835\uDC90\uD835\uDE95\uD835\uDE25 ⓕⓡ\uD835\uDE2A\uD83C\uDD54\uD83C\uDD7D\uD83C\uDD33.",
            YeahText.transform(original, "ransom-soup-1"));
        assertEquals("\uD835\uDE43\uD83C\uDD54\uD835\uDD91\uD83C\uDD5B\uD83C\uDD7E \uD835\uDD99\uD835\uDCEE\uD83C\uDD87\uD835\uDE35 \uD835\uDC60\uD83C\uDD83\uD835\uDCFB\uD835\uDC8A\uD835\uDCF7\uD835\uDDF4 \uD835\uDD92\uD835\uDC32 \uD835\uDDFC\uD835\uDCF5\uD835\uDDF1 \uD835\uDC53\uD835\uDD2F\uD83C\uDD78\uD835\uDE8E\uD835\uDD2B\uD835\uDE8D.",
            YeahText.transform(original, "ransom-soup-2"));
        assertEquals("\uD835\uDCD7\uD835\uDE26\uD835\uDD29\uD835\uDC25\uD835\uDE64 \uD835\uDC61\uD835\uDE8E\uD835\uDEA1\uD835\uDC61 \uD835\uDE00\uD835\uDD65\uD835\uDE9B\uD835\uDC22\uD835\uDE97\uD835\uDC54 \uD835\uDE2E\uD835\uDE6E \uD835\uDC5C\uD835\uDDF9\uD835\uDD89 \uD835\uDE27\uD835\uDE33\uD835\uDD8E\uD835\uDE5A\uD835\uDE97\uD835\uDE8D.",
            YeahText.transform(original, "fontmash-1"));
        assertEquals("\uD835\uDD73\uD835\uDE26\uD835\uDD5D\uD835\uDE61\uD835\uDE98 \uD835\uDD65ℯ\uD835\uDEA1\uD835\uDD31 \uD835\uDC60\uD835\uDE9D\uD835\uDCC7\uD835\uDC8A\uD835\uDCC3\uD835\uDD24 \uD835\uDD5E\uD835\uDC32 \uD835\uDD2C\uD835\uDCC1\uD835\uDD21 \uD835\uDC53\uD835\uDCFB\uD835\uDE92\uD835\uDDF2\uD835\uDCF7\uD835\uDDF1.",
            YeahText.transform(original, "fontmash-2"));
        assertEquals("\uD808\uDC02\uD808\uDC3C\uD808\uDC47\uD808\uDC47\uD808\uDDB8 \uD808\uDE26\uD808\uDC3C\uD808\uDE7D\uD808\uDE26 \uD809\uDD3C\uD808\uDE26\uD808\uDDF2\uD809\uDC15\uD809\uDC16\uD808\uDEDD \uD800\uDFA0\uD808\uDF28 \uD808\uDDB8\uD808\uDC47\uD808\uDC53 \uD800\uDFA3\uD808\uDDF2\uD809\uDC15\uD808\uDC3C\uD809\uDC16\uD808\uDC53.",
            YeahText.transform(original, "cuniform"));
        assertEquals("ꖾꗍꝆꝆꗞ ꖡꗍꘉꖡ ꕷꖡ𐝥ꕯꖦꗱ ꕮꔇ ꗞꝆꕒ ꘘ𐝥ꕯꗍꖦꕒ.",
            YeahText.transform(original, "vai"));
        assertEquals("\uD81A\uDD99\uD81A\uDC22ꛚꛚ\uD81A\uDD55 \uD81A\uDCA7\uD81A\uDC22\uD81A\uDDE6\uD81A\uDCA7 \uD81A\uDE1A\uD81A\uDCA7\uD81A\uDDAA\uD81A\uDD63ꛘꛪ \uD81A\uDC91ꚲ \uD81A\uDD55ꛚ\uD81A\uDDA7 \uD81A\uDE28\uD81A\uDDAA\uD81A\uDD63\uD81A\uDC22ꛘ\uD81A\uDDA7.",
            YeahText.transform(original, "bamum"));
        assertEquals("ᕼᗕᖶᖶᗝ ᐪᗕ᙭ᐪ ᔑᐪᖇᓵᐱᘜ ᗑᖿ ᗝᖶᐅ ᖴᖇᓵᗕᐱᐅ.",
            YeahText.transform(original, "canadian-aboriginal-1"));
        assertEquals("ᖺᕮᘂᘂᗜ ᘕᕮ᙮ᘕ ᔕᘕᖇᑊᘯᕋ ᘻᒉ ᗜᘂᗞ ᒋᖇᑊᕮᘯᗞ.",
            YeahText.transform(original, "canadian-aboriginal-2"));
        assertEquals("ᘵᗴᔈᔈᘓ ᐩᗴᕽᐩ ᔥᐩᖆᒑᘉᙍ ᕬᕃ ᘓᔈᕲ ᓕᖆᒑᗴᘉᕲ.",
            YeahText.transform(original, "canadian-aboriginal-3"));
        assertEquals("ᑋᕪᒻᒻᐤ ᐩᕪᕽᐩ ᔆᐩᔇᑊᐢᕐ ᔿᔉ ᐤᒻᒄ ᕝᔇᑊᕪᐢᒄ.",
            YeahText.transform(original, "canadian-aboriginal-sm"));
        assertEquals("ᕼᕪᒻᒻᐤ ᐩᕪᕽᐩ ᔆᐩᔇᑊᐢᕐ ᔿᔉ ᐤᒻᒄ ᕝᔇᑊᕪᐢᒄ.",
           YeahText.transform(original, "canadian-aboriginal-titlecase"));
        assertEquals("🄗⒠⒧⒧⒪ ⒯⒠⒳⒯ ⒮⒯⒭⒤⒩⒢ ⒨⒴ ⒪⒧⒟ ⒡⒭⒤⒠⒩⒟.",
            YeahText.transform(original, "parenthesis"));
        assertEquals("H⃝e⃝l⃝l⃝o⃝ t⃝e⃝x⃝t⃝ s⃝t⃝r⃝i⃝n⃝g⃝ m⃝y⃝ o⃝l⃝d⃝ f⃝r⃝i⃝e⃝n⃝d⃝.⃝",
            YeahText.transform(original, "big-bubble"));
        assertEquals("H⃣e⃣l⃣l⃣o⃣ t⃣e⃣x⃣t⃣ s⃣t⃣r⃣i⃣n⃣g⃣ m⃣y⃣ o⃣l⃣d⃣ f⃣r⃣i⃣e⃣n⃣d⃣.⃣",
            YeahText.transform(original, "keycap-bubble"));
        assertEquals("卄モㄥㄥ口 匕モ㐅匕 ち匕尺工力ム 爪ソ 口ㄥ刀 下尺工モ力刀.",
            YeahText.transform(original, "cjk-1"));
        assertEquals("卅㠪乚乚囗 十㠪乂十 丂十尺エ几已 从ㄚ 囗乚冂 于尺エ㠪几冂.",
            YeahText.transform(original, "cjk-2"));
        assertEquals("廾乇しし口 丁乇义丁 丂丁尺工刀彑 冊ﾘ 口し问 乍尺工乇刀问.",
            YeahText.transform(original, "cjk-3"));
        assertEquals("卅乇乚ㄥ口 匕乇义匕 丂十尺工刀ム 冊ﾘ 口し冂 乍尺エ乇刀问.",
            YeahText.transform(original, "cjk-combo"));
        // TODO Echo
        assertEquals("廾Ｈ㠪ｅㄥｌ乚ｌ口ｏ  丁ｔモｅ㐅ｘ匕ｔ  丂ｓ十ｔ尺ｒ工ｉ刀ｎ已ｇ  冊ｍﾘｙ  囗ｏㄥｌ刀ｄ  下ｆ尺ｒ工ｉ乇ｅ力ｎ问ｄ.．",
            YeahText.transform(original, "cjk-fw"));
        assertEquals("H͢e͢l͢l͢o͢ ͢t͢e͢x͢t͢ ͢s͢t͢r͢i͢n͢g͢ ͢m͢y͢ ͢o͢l͢d͢ ͢f͢r͢i͢e͢n͢d͢.͢",
            YeahText.transform(original, "under-arrow"));
        assertEquals("H̼e̼l̼l̼o̼ ̼t̼e̼x̼t̼ ̼s̼t̼r̼i̼n̼g̼ ̼m̼y̼ ̼o̼l̼d̼ ̼f̼r̼i̼e̼n̼d̼.̼",
            YeahText.transform(original, "under-seagull"));
        assertEquals("H͙e͙l͙l͙o͙ ͙t͙e͙x͙t͙ ͙s͙t͙r͙i͙n͙g͙ ͙m͙y͙ ͙o͙l͙d͙ ͙f͙r͙i͙e͙n͙d͙.͙",
            YeahText.transform(original, "under-asterisk"));
        assertEquals("H͛e͛l͛l͛o͛ ͛t͛e͛x͛t͛ ͛s͛t͛r͛i͛n͛g͛ ͛m͛y͛ ͛o͛l͛d͛ ͛f͛r͛i͛e͛n͛d͛.͛",
            YeahText.transform(original, "lightening"));
        assertEquals("H͛͛͛e͛͛͛l͛͛͛l͛͛͛o͛͛͛ ͛͛͛t͛͛͛e͛͛͛x͛͛͛t͛͛͛ ͛͛͛s͛͛͛t͛͛͛r͛͛͛i͛͛͛n͛͛͛g͛͛͛ ͛͛͛m͛͛͛y͛͛͛ ͛͛͛o͛͛͛l͛͛͛d͛͛͛ ͛͛͛f͛͛͛r͛͛͛i͛͛͛e͛͛͛n͛͛͛d͛͛͛.͛͛͛",
            YeahText.transform(original, "more-lightning"));
        assertEquals("H̐̈e̐̈l̐̈l̐̈o̐̈ ̐̈t̐̈e̐̈x̐̈t̐̈ ̐̈s̐̈t̐̈r̐̈i̐̈n̐̈g̐̈ ̐̈m̐̈y̐̈ ̐̈o̐̈l̐̈d̐̈ ̐̈f̐̈r̐̈i̐̈e̐̈n̐̈d̐̈.̐̈",
            YeahText.transform(original, "smiley-above"));
        assertEquals("H̑̇̈ȇ̇̈l̑̇̈l̑̇̈ȏ̇̈ ̑̇̈t̑̇̈ȇ̇̈x̑̇̈t̑̇̈ ̑̇̈s̑̇̈t̑̇̈ȓ̇̈ȋ̇̈n̑̇̈g̑̇̈ ̑̇̈m̑̇̈y̑̇̈ ̑̇̈ȏ̇̈l̑̇̈d̑̇̈ ̑̇̈f̑̇̈ȓ̇̈ȋ̇̈ȇ̇̈n̑̇̈d̑̇̈.̑̇̈",
            YeahText.transform(original, "frown-above"));
        assertEquals("H⃟e⃟l⃟l⃟o⃟ t⃟e⃟x⃟t⃟ s⃟t⃟r⃟i⃟n⃟g⃟ m⃟y⃟ o⃟l⃟d⃟ f⃟r⃟i⃟e⃟n⃟d⃟.⃟",
            YeahText.transform(original, "diamond"));
        assertEquals("H⃠e⃠l⃠l⃠o⃠ t⃠e⃠x⃠t⃠ s⃠t⃠r⃠i⃠n⃠g⃠ m⃠y⃠ o⃠l⃠d⃠ f⃠r⃠i⃠e⃠n⃠d⃠.⃠",
            YeahText.transform(original, "do-not-enter"));
        assertEquals("Hello\uD83D\uDC4F text\uD83D\uDC4F string\uD83D\uDC4F my\uD83D\uDC4F old\uD83D\uDC4F friend.",
            YeahText.transform(original, "clapback"));
        assertEquals("Hello\uD83D\uDC4F\uD83C\uDFFB text\uD83D\uDC4F\uD83C\uDFFB string\uD83D\uDC4F\uD83C\uDFFB my\uD83D\uDC4F\uD83C\uDFFB old\uD83D\uDC4F\uD83C\uDFFB friend.",
            YeahText.transform(original, "clapback-light"));
        assertEquals("Hello\uD83D\uDC4F\uD83C\uDFFC text\uD83D\uDC4F\uD83C\uDFFC string\uD83D\uDC4F\uD83C\uDFFC my\uD83D\uDC4F\uD83C\uDFFC old\uD83D\uDC4F\uD83C\uDFFC friend.",
            YeahText.transform(original, "clapback-medium-light"));
        assertEquals("Hello\uD83D\uDC4F\uD83C\uDFFD text\uD83D\uDC4F\uD83C\uDFFD string\uD83D\uDC4F\uD83C\uDFFD my\uD83D\uDC4F\uD83C\uDFFD old\uD83D\uDC4F\uD83C\uDFFD friend.",
            YeahText.transform(original, "clapback-medium"));
        assertEquals("Hello\uD83D\uDC4F\uD83C\uDFFE text\uD83D\uDC4F\uD83C\uDFFE string\uD83D\uDC4F\uD83C\uDFFE my\uD83D\uDC4F\uD83C\uDFFE old\uD83D\uDC4F\uD83C\uDFFE friend.",
            YeahText.transform(original, "clapback-medium-dark"));
        assertEquals("Hello\uD83D\uDC4F\uD83C\uDFFF text\uD83D\uDC4F\uD83C\uDFFF string\uD83D\uDC4F\uD83C\uDFFF my\uD83D\uDC4F\uD83C\uDFFF old\uD83D\uDC4F\uD83C\uDFFF friend.",
            YeahText.transform(original, "clapback-dark"));
        // TODO
//        assertEquals("Hello\uD83D\uDC4F\uD83C\uDFFB text\uD83D\uDC4F\uD83C\uDFFC string\uD83D\uDC4F\uD83C\uDFFD my\uD83D\uDC4F\uD83C\uDFFE old\uD83D\uDC4F\uD83C\uDFFF friend.",
//            YeahText.transform(original, "clapback-rainbow"));
//        assertEquals("Hello❤️text\uD83E\uDDE1string\uD83D\uDC9Bmy\uD83D\uDC9Aold\uD83D\uDC99friend.",
//            YeahText.transform(original, "rainbow-hearts"));
//        assertEquals("Hello\uD83E\uDDE1text\uD83D\uDC9Bstring\uD83D\uDC9Amy\uD83D\uDC99old\uD83D\uDC9Cfriend.",
//            YeahText.transform(original, "rainbow-hearts-no-red"));
        assertEquals("Hello❤️text❤️string❤️my❤️old❤️friend.",
            YeahText.transform(original, "red-hearts"));
        assertEquals("Hello\uD83E\uDDE1text\uD83E\uDDE1string\uD83E\uDDE1my\uD83E\uDDE1old\uD83E\uDDE1friend.",
            YeahText.transform(original, "orange-hearts"));
        assertEquals("Hello\uD83D\uDC9Btext\uD83D\uDC9Bstring\uD83D\uDC9Bmy\uD83D\uDC9Bold\uD83D\uDC9Bfriend.",
            YeahText.transform(original, "yellow-hearts"));
        assertEquals("Hello\uD83D\uDC9Atext\uD83D\uDC9Astring\uD83D\uDC9Amy\uD83D\uDC9Aold\uD83D\uDC9Afriend.",
            YeahText.transform(original, "green-hearts"));
        assertEquals("Hello\uD83D\uDC99text\uD83D\uDC99string\uD83D\uDC99my\uD83D\uDC99old\uD83D\uDC99friend.",
            YeahText.transform(original, "blue-hearts"));
        assertEquals("Hello\uD83D\uDC9Ctext\uD83D\uDC9Cstring\uD83D\uDC9Cmy\uD83D\uDC9Cold\uD83D\uDC9Cfriend.",
            YeahText.transform(original, "purple-hearts"));
        assertEquals("✌️Hello✌️ ✌️text✌️ ✌️string✌️ ✌️my✌️ ✌️old✌️ ✌️friend.✌️",
            YeahText.transform(original, "air-quotes"));
        assertEquals("█████ ████ ██████ ██ ███ ███████",
            YeahText.transform(original, "classified"));
    }
}