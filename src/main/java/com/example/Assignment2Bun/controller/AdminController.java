package com.example.Assignment2Bun.controller;

import com.example.Assignment2Bun.model.*;
import com.example.Assignment2Bun.model.dto.*;
import com.example.Assignment2Bun.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)

public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    final String secretKey = "JHKLXABYZC!!!!";
    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);
    private static final ResponseEntity<User> SUCCESS_RESPONSE = new ResponseEntity<>(HttpStatus.OK);
    private static final ResponseEntity<User> FAILURE_RESPONSE = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    @PostMapping(path = "/addAdmin") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser(@RequestBody DTOUser dtoUser) throws Exception {
        CipherDecrypt cipherDecrypt= new CipherDecrypt();
       String encryptedPass= cipherDecrypt.encrypt(dtoUser.getPassword(),secretKey);
        Admin admin = new Admin(dtoUser.getFirstName(), dtoUser.getLastName(), dtoUser.getEmail(), encryptedPass, dtoUser.getUsername(),dtoUser.getIsAdmin());
        if(!adminService.findByUsername(dtoUser.getUsername()).isPresent()) {
            adminService.save(admin);
            return "Saved admin";
        }
        return "Admin already exists";
    }


    @GetMapping(path = "/findUserByUsernameAdmin/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) throws Exception {
        Optional<Admin> admin=adminService.findByUsername(username);
        CipherDecrypt cipherDecrypt= new CipherDecrypt();
        String decryptPass= cipherDecrypt.decrypt(admin.get().getPassword(),secretKey);
        admin.get().setPassword(decryptPass);
        return ResponseEntity.status(HttpStatus.OK).body(admin.orElse(new Admin()));

    }

    @Transactional
    @DeleteMapping(path = "/deleteUserByUsernameAdmin/{username}")
    public ResponseEntity<User> deleteUserByUsername(@PathVariable String username) {

        try {
            adminService.deleteByUsername(username);
            return SUCCESS_RESPONSE;

        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to delete user with username = " + username, e.getMessage());
            return FAILURE_RESPONSE;
        }

    }


    @PostMapping(path = "/addRestaurant") // Map ONLY POST Requests
    public @ResponseBody
    String addRestaurant(@RequestBody DTORestaurant dtoRestaurant) {
        Optional<Admin> admin=adminService.findByUsername(dtoRestaurant.getAdminUsername());
            Restaurant restaurant = new Restaurant(dtoRestaurant.getName(), dtoRestaurant.getLocation());
            restaurant.setAdmin(admin.get());
            restaurant.setZone(dtoRestaurant.getZoneName());
            admin.get().setRestaurant(restaurant);
            restaurantService.save(restaurant);

        return "Saved restaurant";
    }

    @Transactional
    @DeleteMapping(path = "/deleteRestaurantByName/{name}")
    public void deleteRestaurantByName(@PathVariable String name) {

        try {
            restaurantService.deleteByName(name);

        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to restaurant zone with name = " + name, e.getMessage());
        }

    }


    @PostMapping(path = "/addCategory")
    public @ResponseBody
    String addCategory(@RequestBody DTOCategory dtoCategory) {
        Optional<Menu> menu = menuService.findByMenuName(dtoCategory.getMenuName());
        Category category = new Category(dtoCategory.getName());
        if (menu.isPresent()) {
            if (!menu.get().getCategoryList().contains(category))
                menu.get().addCategory(category);
            category.addMenu(menu.get());
            category.setRestaurantID(menu.get().getRestaurant().getId());
            categoryService.save(category);
        }

        return "Saved category";
    }

    @PostMapping(path = "/addMenu") // Map ONLY POST Requests
    public @ResponseBody
    String addMenu(@RequestBody DTOMenu dtoMenu) {

        Optional<Restaurant> restaurant = restaurantService.findByName(dtoMenu.getRestaurantName());
        Iterable<Restaurant> allRes=restaurantService.findAll();

        if (restaurant.isPresent()) {
            Menu menu = new Menu(dtoMenu.getMenuName(), restaurant.get());
            menu.setRestaurantIdMenu(restaurant.get().getId());
            if (restaurant.get().getMenu() == null) {
                restaurant.get().setMenu(menu);
            }
            menuService.save(menu);
        }

        return "Saved menu";
    }

    @PostMapping(path = "/addFood")
    public @ResponseBody
    String addFood(@RequestBody DTOFood dtoFood) {
        Optional<Restaurant> restaurant= restaurantService.findByName(dtoFood.getRestaurantName());
        Optional<Category> category = categoryService.findByNameAndRestaurantID(dtoFood.getCategoryName(),restaurant.get().getId());
        if (category.isPresent()) {
            Food food = new Food(dtoFood.getName(), dtoFood.getDescription(), dtoFood.getPrice(), category.get());
            food.setRestaurantId(restaurant.get());
            restaurant.get().addFood(food);
            //if (category.get().getFoodList() == null) {
                category.get().addFood(food);
            //}
            foodService.save(food);
        }
        return "Saved food";
    }
    @GetMapping(path="/getCategoriesFromARestaurant/{name}")
    public ResponseEntity<List<Category>>  findCategoriesFromRestaurant(@PathVariable  String name) {
        Optional<Restaurant> restaurant= restaurantService.findByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant.get().getMenu().getCategoryList());

    }
    @GetMapping(path="/allRestaurants")
    public Iterable<Restaurant>   allRestaurants() {
        return  restaurantService.findAll();

    }
    @GetMapping(path="/allOrders/{restName}")
    public Iterable<Order>  allOrders(@PathVariable String restName) {
        Optional<Restaurant> restaurant= restaurantService.findByName(restName);
        List<Order> allOrders= new ArrayList<>();
            for (Order order: restaurant.get().getOrders()) {
                allOrders.add(order);
            }

        return  allOrders;

    }
    @GetMapping(path="/findOrdersByStatus/{orderStatus}/{restName}")
    public Iterable<Order>  findOrdersByStatus(@PathVariable String orderStatus,@PathVariable String restName) {
        Optional<Restaurant> restaurant= restaurantService.findByName(restName);
        List<Order> allOrders= new ArrayList<>();
        for (Order order: restaurant.get().getOrders()) {
            if(order.getOrderStatus().toString().equals(orderStatus))
                allOrders.add(order);
        }

        return  allOrders;

    }
    @GetMapping(path="/findMenuForAllRestaurants")
    public ResponseEntity<List<Menu>>  menuForAllRestaurants() {
        Iterable<Restaurant> allRestaurants= restaurantService.findAll();
        List<Menu> allMenus= new ArrayList<>();
        for (Restaurant restaurant: allRestaurants) {
            Menu currMenu= restaurant.getMenu();
            allMenus.add(currMenu);
        }
        return ResponseEntity.status(HttpStatus.OK).body(allMenus);

    }
    @GetMapping(path="/getFoodFromACategory/{name}")
    public ResponseEntity<List<Food>>  findFoodByCategory(@PathVariable  String name) {
        Optional<Category> category= categoryService.findByName(name);
        List<Food> intermidiateFood= new ArrayList<>();
        for (Food food: category.get().getFoodList()) {
                Food newFood= new Food(food.getName(),food.getDescription(),food.getPrice());
                intermidiateFood.add(newFood);
        }
        return ResponseEntity.status(HttpStatus.OK).body(intermidiateFood);


    }
    @PutMapping(path="/changeStatusOrder")
    public Order updateOrder(@RequestBody  OrderDTOForStatus orderDTOForStatus) {
        Optional<Order> order= orderService.findByOrderId(orderDTOForStatus.getOrderId());
        User user= order.get().getClient();
        if(orderDTOForStatus.getOrderStatus().equals("DECLINED"))
        {

            user.getClientOrders().remove(order.get());

        }
        orderService.updateOrder(order.get(),orderDTOForStatus.getOrderStatus());

        return order.get();

    }
    @GetMapping(path="/allAdmins")
    public @ResponseBody
    Iterable<Admin> getAllUsers() {
        // This returns a JSON or XML with the users
        return adminService.findAll();
    }

}

