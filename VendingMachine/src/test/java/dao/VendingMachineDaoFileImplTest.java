package dao;

import dto.Item;
import org.junit.jupiter.api.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VendingMachineDaoFileImplTest {
    VendingMachineDao dao = new VendingMachineDaoFileImpl("testVendingMachine.txt");
    File file = new File("testVendingMachine.txt");

    @BeforeAll
    public static void setUpClass(){
    }

    @AfterAll
    public static void tearDownClass(){
    }

    @BeforeEach
    public void setUp() throws Exception{
        try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("testVendingMachine.txt")))){
            writer.write("Coca-Cola::1.75::2"
            + "\nSprite::1.75::0"
            + "\nWater::1.25::1");
        }

        Item item = new Item("Coca-Cola");
        item.setItemCost(new BigDecimal("1.75"));
        item.setNumItems(2);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetVendingMachineItems()throws Exception{
        List<Item> allItems = dao.getVendingMachineItems();
        assertEquals(3, allItems.size());
    }

    @Test
    public void testGetItem() throws Exception{
        Item item = new Item("Sprite");
        item.setItemCost(new BigDecimal("1.75"));
        item.setNumItems(0);
        Item shouldBeSprite = dao.getItem("Sprite");
        assertEquals(item,shouldBeSprite);
    }

    @Test
    public void testPurchaseItem() throws Exception{
        dao.purchaseItem("Coca-Cola");
        assertEquals(1, dao.getItem("Coca-Cola").getNumItems());
    }
}