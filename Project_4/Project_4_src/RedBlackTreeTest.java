import org.junit.*;
import java.util.NoSuchElementException;

public class RedBlackTreeTest
{
  IBinarySearchTree<Integer,String> tree;
  @Before
  public void setUp()
  {
    tree = new RedBlackTree<Integer,String>();
  }

  private void setUpSingle()
  {
    tree.put(10, "Ten");
  }

  private void setUpMulti()
  {
    tree.put(8, "Eight");
    tree.put(3, "Three");
    tree.put(10, "Ten");
    tree.put(1, "One");
    tree.put(6, "Six");
    tree.put(14, "Fourteen");
    tree.put(4, "Four");
    tree.put(7, "Seven");
    tree.put(13, "Thirteen");
  }

  private void setUpLinear()
  {
    tree.put(1, "One");
    tree.put(2, "Two");
    tree.put(3, "Three");
    tree.put(4, "Four");
    tree.put(5, "Five");
    tree.put(6, "Six");
    tree.put(7, "Seven");
    tree.put(8, "Eight");
    tree.put(9, "Nine");
  }

  @Test
  public void testInitialization()
  {
    /* testing setUp */
  }

  @Test
  public void testSizeEmpty()
  {
    Assert.assertEquals(0, tree.size());
  }

  @Test
  public void testToStringEmpty()
  {
    Assert.assertEquals("[]", tree.toString());
  }

  @Test
  public void testTreeStringEmpty()
  {
    Assert.assertEquals("[]", tree.getTreeString());
  }

  @Test
  public void testHeightEmpty()
  {
    Assert.assertEquals(0,tree.getHeight());
  }

  @Test
  public void testGetSingle()
  {
    setUpSingle();
    Assert.assertEquals("Ten", tree.get(10));
  }

  @Test
  public void testSizeSingle()
  {
    setUpSingle();
    Assert.assertEquals(1, tree.size());
  }

  @Test
  public void testToStringSingle()
  {
    setUpSingle();
    Assert.assertEquals("[10: Ten]", tree.toString());
  }

  @Test
  public void testTreeStringSingle()
  {
    setUpSingle();
    Assert.assertEquals("[Black, 10, null, null]", tree.getTreeString());
  }

  @Test
  public void testHeightSingle()
  {
    setUpSingle();
    Assert.assertEquals(0,tree.getHeight());
  }

  @Test
  public void testGetMulti()
  {
    setUpMulti();
    Assert.assertEquals("Ten", tree.get(10));
    Assert.assertEquals("Six", tree.get(6));
    Assert.assertEquals("Thirteen", tree.get(13));
    Assert.assertEquals("One", tree.get(1));
  }

  @Test(expected=NoSuchElementException.class)
  public void testGetExceptionMulti()
  {
    setUpMulti();
    tree.get(2);
  }

  @Test
  public void testSizeMulti()
  {
    setUpMulti();
    Assert.assertEquals(9, tree.size());
  }

  @Test
  public void testToStringMulti()
  {
    setUpMulti();
    Assert.assertEquals("[1: One, 3: Three, 4: Four, 6: Six, 7: Seven, 8: Eight, 10: Ten, 13: Thirteen, 14: Fourteen]", tree.toString());
  }

  @Test
  public void testTreeStringMulti()
  {
    setUpMulti();
    Assert.assertEquals("[Black, 8, [Red, 3, [Black, 1, null, null], [Black, 6, [Red, 4, null, null], [Red, 7, null, null]]], [Black, 13, [Red, 10, null, null], [Red, 14, null, null]]]", tree.getTreeString());
  }

  @Test
  public void testHeightMulti()
  {
    setUpMulti();
    Assert.assertEquals(3,tree.getHeight());
  }

  @Test
  public void testGetLinear()
  {
    setUpLinear();
    Assert.assertEquals("One", tree.get(1));
    Assert.assertEquals("Four", tree.get(4));
    Assert.assertEquals("Six", tree.get(6));
    Assert.assertEquals("Nine", tree.get(9));
  }

  @Test
  public void testSizeLinear()
  {
    setUpLinear();
    Assert.assertEquals(9, tree.size());
  }

  @Test
  public void testToStringLinear()
  {
    setUpLinear();
    Assert.assertEquals("[1: One, 2: Two, 3: Three, 4: Four, 5: Five, 6: Six, 7: Seven, 8: Eight, 9: Nine]", tree.toString());
  }

  @Test
  public void testTreeStringLinear()
  {
    setUpLinear();
    Assert.assertEquals("[Black, 4, [Red, 2, [Black, 1, null, null], [Black, 3, null, null]], [Red, 6, [Black, 5, null, null], [Black, 8, [Red, 7, null, null], [Red, 9, null, null]]]]", tree.getTreeString());
  }

  @Test
  public void testHeightLinear()
  {
    setUpLinear();
    Assert.assertEquals(3,tree.getHeight());
  }
}
