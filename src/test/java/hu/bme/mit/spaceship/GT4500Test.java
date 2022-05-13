package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryTorpedo;
  private TorpedoStore secondaryTorpedo;

  @BeforeEach
  public void init(){
    primaryTorpedo = mock(TorpedoStore.class);
    secondaryTorpedo = mock(TorpedoStore.class);
    this.ship = new GT4500(primaryTorpedo, secondaryTorpedo);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryTorpedo.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primaryTorpedo, times(1)).fire(1);
    
  }

  @Test
  public void fireTorpedo_Single_Failure(){
    // Arrange
    when(primaryTorpedo.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(primaryTorpedo, times(1)).fire(1);
    
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primaryTorpedo.fire(1)).thenReturn(true);
    when(secondaryTorpedo.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primaryTorpedo, times(1)).fire(1);
    verify(secondaryTorpedo, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_secondary_failure(){
    // Arrange
    when(primaryTorpedo.fire(1)).thenReturn(true);
    when(secondaryTorpedo.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(primaryTorpedo, times(1)).fire(1);
    verify(secondaryTorpedo, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_primary_failure(){
    // Arrange
    when(primaryTorpedo.fire(1)).thenReturn(false);
    when(secondaryTorpedo.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_All_empty_secondary_failure(){
    // Arrange
    when(secondaryTorpedo.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);

  }

  @Test
  public void fireTorpedo_All_empty_primary_failure(){
    // Arrange
    when(primaryTorpedo.isEmpty()).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }


}
