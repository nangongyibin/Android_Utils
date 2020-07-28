package com.ngyb.utils.constant;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/16 20:41
 */
public class Constant {


    /**
     * 移动：134、135、136、137、138、139、150、151、157（TD）、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、180、189、177（1349卫通）
     * “[1]”代表第一位为数字1
     * “[358]”代表第二位可以为3、5、7、8中的一个
     * “\\d{9}”代表后面是可以是0-9的数字，有9位。
     */
    public static final String TelRegex = "[1][3578]\\d{9}";
    //联通的3G为UMTS或HSDPA,移动和联通的2G为GPRS或EGDE,电信的2G为CDMA，电信的3G为EVDO
    /**
     * Unknown network class
     */
    public static final int NETWORK_CLASS_UNKNOWN = 0;
    /**
     * wifi net workd
     */
    public static final int NETWORK_WIFI = 1;

    /**
     * "2G" networks
     */
    public static final int NETWORK_CLASS_2_G = 2;

    /**
     * "3G" networks
     */
    public static final int NETWORK_CLASS_3_G = 3;

    /**
     * "4G" networks
     */
    public static final int NETWORK_CLASS_4_G = 4;
}
