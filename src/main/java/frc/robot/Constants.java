package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    public static final int kLeftMotor1Port = 12;
    public static final int kLeftMotor2Port = 13;
    public static final int kRightMotor1Port = 15;
    public static final int kRightMotor2Port = 14;


    public static final int kEncoderCPR = 1024;
    public static final double kWheelDiameterInches = 6;
    public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;
  }

  public static final class IntakeConstants {
    public static final int kIntakeSolenoidModule = 10;
    public static final int kIntakeSolenoidPort = 0;
    public static final int kIntakeMotorCANPort = 0;
    public static final double kIntakeMotorForwardSpeed = 0.5;
    public static final double kIntakeMotorReverseSpeed = -0.5;
    public static final double kIntakeMotorStopSpeed = 0.0;
  }

  public static final class HopperConstants {
    public static final int kHopperMotorCANPort = 1;
    public static final double kHopperMotorForwardSpeed = 0.5;
    public static final double kHopperMotorReverseSpeed = -0.5;
    public static final double kHopperMotorStopSpeed = 0.0;
  }

  public static final class IndexerConstants {
    public static final int kIndexerMotorCANPort = 2;
    public static final double kIndexerMotorForwardSpeed = 0.5;
    public static final double kIndexerMotorReverseSpeed = -0.5;
    public static final double kIndexerMotorStopSpeed = 0.0;
  }

  public static final class ShooterConstants {
    public static final int kShooterMotorCANPort = 9;
    public static final double kShooterMotorForwardSpeed = 0.1;
    public static final double kShooterMotorReverseSpeed = -0.1;
    public static final double kShooterMotorStopSpeed = 0.0;
  }

  public static final class LiftConstants {
    public static final int kLiftMotorCANPort = 3;
    public static final double kLiftMotorForwardSpeed = 0.5;
    public static final double kLiftMotorReverseSpeed = -0.5;
    public static final double kLiftMotorStopSpeed = 0.0;
  }
  public static final class TraverseConstants {
    public static final int kTraverseMotorCANPort = 4;
    public static final double kTraverseMotorForwardSpeed = 0.5;
    public static final double kTraverseMotorReverseSpeed = -0.5;
    public static final double kTraverseMotorStopSpeed = 0.0;
  }
  public static final class WOFConstants {
    public static final int kWOFMotorCANPort = 5;
    public static final double kWOFMotorForwardSpeed = 0.5;
    public static final double kWOFMotorReverseSpeed = -0.5;
    public static final double kWOFMotorStopSpeed = 0.0;
  }

  public static final class AutoConstants {
    public static final double kAutoDriveDistanceInches = 60;
    public static final double kAutoBackupDistanceInches = 20;
    public static final double kAutoDriveSpeed = 0.5;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 1;
    public static final int kCoDriverControllerPort = 2;
  }
}
