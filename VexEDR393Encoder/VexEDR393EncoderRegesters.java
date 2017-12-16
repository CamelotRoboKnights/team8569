package org.firstinspires.ftc.teamcode.team.VexEDR393Encoder;

/**
 * Created by christopherradcliffe on 12/11/17.
 */

public class VexEDR393EncoderRegesters {
    public static final byte VEX_EDR393_READ_ENCODER = 0x20;
    public static final int VEX_EDR393_READ_VERSION_NUMBER_BYTE_INDEX = 0;
    public static final int VEX_EDR393_READ_TYPE_BYTE_INDEX = 1;
    public static final int VEX_EDR393_READ_BOARD_ID_BYTE_INDEX = 2;
    public static final int VEX_EDR393_READ_DEVICE_STATUS_BYTE_INDEX = 3;
    public static final int VEX_EDR393_READ_IS_TERMINATED_BIT_INDEX = 0;
    public static final int VEX_EDR393_READ_IS_OVERFLOW_BIT_INDEX = 1;
    public static final int VEX_EDR393_READ_IS_DIAGNOSTIC_MODE_ON_BIT_INDEX = 2;
    public static final int VEX_EDR393_READ_DIRECTION_BIT_INDEX = 3;

    public static final byte VEX_EDR393_READ_SIGNED_VELOCITY = 0x3E-0x3F;
}
