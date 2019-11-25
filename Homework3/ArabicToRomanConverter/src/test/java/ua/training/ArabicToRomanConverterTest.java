package ua.training;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArabicToRomanConverterTest {
    public static ArabicToRomanConverter arabicToRomanConverter;

    @BeforeClass
    public static void init() {
        arabicToRomanConverter = new ArabicToRomanConverter();
    }

    @Test
    public void convertArabicToRoman_arabic1_romanI() {
        Assert.assertEquals("I", arabicToRomanConverter.convertArabicToRoman(1));
    }

    @Test
    public void convertArabicToRoman_arabic2_romanII() {
        Assert.assertEquals("II", arabicToRomanConverter.convertArabicToRoman(2));
    }

    @Test
    public void convertArabicToRoman_arabic3_romanIII() {
        Assert.assertEquals("III", arabicToRomanConverter.convertArabicToRoman(3));
    }

    @Test
    public void convertArabicToRoman_arabic4_romanIV() {
        Assert.assertEquals("IV", arabicToRomanConverter.convertArabicToRoman(4));
    }

    @Test
    public void convertArabicToRoman_arabic0_roman_() {
        Assert.assertEquals("", arabicToRomanConverter.convertArabicToRoman(0));
    }

    @Test
    public void convertArabicToRoman_arabic5_romanV() {
        Assert.assertEquals("V", arabicToRomanConverter.convertArabicToRoman(5));
    }

}