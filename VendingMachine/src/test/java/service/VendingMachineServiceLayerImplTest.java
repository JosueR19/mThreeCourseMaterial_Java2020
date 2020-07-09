package service;

import dao.VendingMachineDao;
import dao.VendingMachineDaoFileImpl;
import dto.Change;
import dto.Item;
import org.junit.jupiter.api.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VendingMachineServiceLayerImplTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerImplTest(){
        VendingMachineDao dao = new VendingMachineDaoFileImpl("testVendingMachine.txt");
        File file = new File("testVendingMachine.txt");
        Change change = new Change();
        Item item = new Item("Sprite");

        service = new VendingMachineServiceLayerImpl(dao, change);
    }

    @BeforeAll
    public static void setUpClass(){
    }

    @AfterAll
    public static void tearDownClass(){
    }

    @BeforeEach
    public void setUp() throws Exception{
        try(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("testVendingMachine.txt")))){
            writer.write ("Coca-Cola::1.75::3"
            + "\nSprite::1.75::2"
            + "\nWater::1.25::0");
        }
        Item item = new Item("Coca-Cola");
        item.setItemCost(new BigDecimal("1.75"));
        item.setNumItems(3);

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetVendingMachineItems() throws Exception{
        List<Item> allItems = service.getVendingMachineItems();
        assertEquals(3, allItems.size());
    }

    @Test
    public void testGetItem() throws Exception{
        Item item = new Item("Sprite");
        item.setItemCost(new BigDecimal("1.75"));
        item.setNumItems(2);
        Item shouldBeSprite = service.getItem("Sprite");
        assertEquals(item,shouldBeSprite);
    }

    @Test
    public void testPurchaseItem() throws Exception{
        String purchaseItem = service.purchaseItem("Coca-Cola", new BigDecimal("1.95"));
        assertEquals("Your change is \n0 Quarters \n2 Dimes \n0 Nickels \n0 Pennies \n", purchaseItem);
    }

    @Test
    public void testPurchaseItemInsufficientFundsException() throws Exception{
        try{
            service.purchaseItem("Coca-Cola", new BigDecimal(".50"));
        }catch(InsufficientFundsException ex){
        }
    }

    @Test
    public void testNoInventoryException() throws Exception {
        try {
            service.purchaseItem("Water", new BigDecimal("4.00"));
        } catch (NoItemInventoryException ex) {
        }
    }

    //test for failures in purchaseItem
}