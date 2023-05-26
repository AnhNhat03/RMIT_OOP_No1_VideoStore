package com.no1.geniestore.controllers;

import com.no1.geniestore.accounts.Account;
import com.no1.geniestore.constants.Genre;
import com.no1.geniestore.constants.ItemType;
import com.no1.geniestore.constants.LoanType;
import com.no1.geniestore.products.Item;
import com.no1.geniestore.products.Order;
import com.no1.geniestore.products.OrderDetails;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.no1.geniestore.products.ManagementSystem.*;


public class AdminDetailPageController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private AnchorPane addItemForm;
    @FXML
    private AnchorPane addOrderForm;
    @FXML
    private AnchorPane addCustomerForm;
    @FXML
    private TableColumn<ItemData, String> addItemColCopies;

    @FXML
    private TableColumn<ItemData, Genre> addItemColGenre;

    @FXML
    private TableColumn<ItemData, String> addItemColId;

    @FXML
    private TableColumn<ItemData, String> addItemColTitle;

    @FXML
    private TableColumn<ItemData, LoanType> addItemColLoanType;

    @FXML
    private TableColumn<ItemData, String> addItemColRemaining;

    @FXML
    private TableColumn<ItemData, String> addItemColRentalFee;

    @FXML
    private TableColumn<ItemData, ItemType> addItemColType;

    @FXML
    private TableColumn<ItemData, String> addItemColYear;

    @FXML
    private TextField addItemSearch;

    @FXML
    private TableView<ItemData> addItemTableView;

    @FXML
    private Button close;

    @FXML
    private Spinner<Integer> copies;

    @FXML
    private Button customersBtn;

    @FXML
    private ComboBox<Genre> genreComboBox;

    @FXML
    private Button ordersBtn;

    @FXML
    private Button itemsBtn;

    @FXML
    private ComboBox<LoanType> loanTypeComboBox;

    @FXML
    private Button logout;

    @FXML
    private Button minimize;

    @FXML
    private Spinner<Integer> remaining;

    @FXML
    private TextField itemId;

    @FXML
    private TextField itemTitle;

    @FXML
    private Label itemTitleError;

    @FXML
    private TextField rentalFee;

    @FXML
    private Label rentalFeeError;

    @FXML
    private ComboBox<ItemType> typeComboBox;

    @FXML
    private Label username;
    @FXML
    private ComboBox<Integer> yearComboBox;

    @FXML
    private Label yearComboBoxError;

    @FXML
    private ImageView addItemImage;
    private Image image;
    @FXML
    private Label imagePath;

    private ObservableList<ItemData> addItemList;

    /* Add Account Form */
    @FXML
    private TextField addAccountID;
    @FXML
    private TextField addAccountName;
    @FXML
    private TextField addAccountAddress;
    @FXML
    private TextField addAccountPhone;
    @FXML
    private ComboBox<String> addAccountLevelComboBox;
    @FXML
    private TextField addAccountUsername;
    @FXML
    private TextField addAccountReturnedItems;
    @FXML
    private TextField addAccountPoints;

    private ObservableList<Account> addAccountList;
    @FXML
    private TableView<Account> addAccountTableView;
    @FXML
    private TableColumn<Account, String> addAccountColId;
    @FXML
    private TableColumn<Account, String> addAccountColName;
    @FXML
    private TableColumn<Account, String> addAccountColAddress;
    @FXML
    private TableColumn<Account, String> addAccountColPhone;
    @FXML
    private TableColumn<Account, String> addAccountColUsername;
    @FXML
    private TableColumn<Account, String> addAccountColLevel;
    @FXML
    private TableColumn<Account, String> addAccountColReturnedItems;
    @FXML
    private TableColumn<Account, String> addAccountColPoints;


    /* Order Form */
    @FXML
    private TextField orderOrderTextField;
    @FXML
    private TextField orderItemIDTextField;
    @FXML
    private TextField orderTitleTextField;

    private ObservableList<OrderData> adminOrderList;
    @FXML
    private TableView<OrderData> orderTableView;
    @FXML
    private TableColumn<?,?> orderColOrderId;
    @FXML
    private TableColumn<?,?> orderColCustomerId;
    @FXML
    private TableColumn<?,?> orderColCustomerName;
    @FXML
    private TableColumn<?,?> orderColDate;
    @FXML
    private TableColumn<?,?> orderColDiscount;
    @FXML
    private TableColumn<?,?> orderColTotal;

    private ObservableList<RentalData> adminRentList;
    @FXML
    private TableView<RentalData> orderRentTableView;
    @FXML
    private TableColumn<RentalData, String> orderColRentId;
    @FXML
    private TableColumn<RentalData, String> orderColRentTitle;
    @FXML
    private TableColumn<RentalData, ItemType> orderColRentItemType;
    @FXML
    private TableColumn<RentalData, String> orderColRentQty;
    @FXML
    private TableColumn<RentalData, String> orderColRentFee;
    @FXML
    private TableColumn<RentalData, String> orderColRentDueDate;
    @FXML
    private TableColumn<RentalData, String> orderColRentStatus;
    @FXML
    private Button returnBtn;


    /* Add Item Form methods */
    public void addItemShowListData() {
        addItemColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        addItemColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        addItemColYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        addItemColType.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        addItemColGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addItemColLoanType.setCellValueFactory(new PropertyValueFactory<>("loanType"));
        addItemColRentalFee.setCellValueFactory(new PropertyValueFactory<>("rentalFee"));
        addItemColCopies.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
        addItemColRemaining.setCellValueFactory(new PropertyValueFactory<>("remainingCopies"));

        addItemTableView.setItems(addItemList);
    }

    public void addItemSelect() {
        ItemData itemData = addItemTableView.getSelectionModel().getSelectedItem();
        int num = addItemTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        itemId.setText(itemData.getId());
        itemTitle.setText(itemData.getTitle());
        yearComboBox.setValue(itemData.getYear());
        typeComboBox.setValue(itemData.getItemType());
        genreComboBox.setValue(itemData.getGenre());
        loanTypeComboBox.setValue(itemData.getLoanType());
        rentalFee.setText(String.valueOf(itemData.getRentalFee()));
        copies.getValueFactory().setValue(itemData.getTotalCopies());
        remaining.getValueFactory().setValue(itemData.getRemainingCopies());

//        String uri = AdminDetailPageController.getResource("/assets/lastchristmas.jpg").toString();
        String uri = "file:images/" + itemData.getImage();
        image = new Image(uri, 200, 200, false, true);
        addItemImage.setImage(image);
        imagePath.setText(itemData.getImage());

//        for (Integer year : yearComboList) {
//            if (year == itemData.getYear()) {
//                yearComboBox.setValue(year);
//            }
//        }
    }

    public void updateItemView() {
        System.out.println("update called");

        for (ItemData item : addItemList) {
            if (item.getId().equals(itemId.getText())) {
                item.setTitle(itemTitle.getText());
                item.setYear(Integer.parseInt(String.valueOf(yearComboBox.getValue())));
                item.setItemType(typeComboBox.getValue());
                item.setGenre(genreComboBox.getValue());
                item.setLoanType(loanTypeComboBox.getValue());
                item.setRentalFee(Double.parseDouble(rentalFee.getText()));
                item.setRemainingCopies((copies.getValue() - item.getTotalCopies()) + item.getRemainingCopies());
                item.setTotalCopies((int) copies.getValue());
                item.setImage(imagePath.getText());

                addItemTableView.refresh();

                // Update API
                updateItem(item.getId(), itemTitle.getText(), loanTypeComboBox.getValue(), Double.parseDouble(rentalFee.getText()), genreComboBox.getValue(), copies.getValue(), remaining.getValue());

                // Alert update successfully
                Alert updateAlert = new Alert(Alert.AlertType.INFORMATION);
                updateAlert.setHeaderText(null);
                updateAlert.setContentText("Update item successfully");
                updateAlert.showAndWait();


                return;
            }
        }
    }

    public void addItemClear() {

    }

    public void addItemInsertImage() {
        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(addItemForm.getScene().getWindow());

        if (file != null) {
            // command to add path of image
            imagePath.setText(file.getName());
            image = new Image(file.toURI().toString(), 200, 200, false, true);
            addItemImage.setImage(image);
        }
    }

    public ArrayList<Integer> yearList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1900; i <= 2023; i++) {
            list.add(i);
        }
        return list;
    }

    public ArrayList<Integer> yearComboList = yearList();

    public void close() throws ParserConfigurationException, FileNotFoundException, TransformerException {
        // Save items info to file before closing the application
        new ItemListParser().saveItemFile();

        // close the app
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    /* Add Account Form methods */
    public void addAccountShowListData() {
        addAccountList = FXCollections.observableArrayList(accountList);

        addAccountColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        addAccountColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        addAccountColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        addAccountColPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addAccountColUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        addAccountColLevel.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        addAccountColReturnedItems.setCellValueFactory(new PropertyValueFactory<>("totalReturnedItems"));
        addAccountColPoints.setCellValueFactory(new PropertyValueFactory<>("rewardPoints"));

//        addAccountTableView.setItems((ObservableList<Account>) accountList);
        addAccountTableView.setItems(addAccountList);
    }

    public void addAccountSelect() {
        Account account = addAccountTableView.getSelectionModel().getSelectedItem();
        int num = addAccountTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addAccountID.setText(account.getId());
        addAccountName.setText(account.getName());
        addAccountAddress.setText(account.getAddress());
        addAccountPhone.setText(account.getPhone());
        addAccountLevelComboBox.setValue(account.getAccountType());
        addAccountUsername.setText(account.getUsername());
        addAccountReturnedItems.setText(String.valueOf(account.getTotalReturnedItems()));
        addAccountPoints.setText(String.valueOf(account.getRewardPoints()));

    }

    /* Order View methods */
    public void orderShowListData() {
        adminOrderList = createOrderShowListData();

        orderColOrderId.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        orderColCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        orderColCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        orderColDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        orderColDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        orderColTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        orderTableView.setItems(adminOrderList);
    }

    public static ObservableList<OrderData> createOrderShowListData() {
        ObservableList<OrderData> orderDataList = FXCollections.observableArrayList();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (Order order : orderList) {
            String orderID = order.getOrderID();
            String customerID = order.getOwner().getId();
            String customerName = order.getOwner().getName();
            String date = "";
            for (Item item : order.getOrder().keySet()) {
                date = dateFormat.format(order.getOrder().get(item).getLoanDate());
                break;
            }

            double discount = order.getTotalDiscount();
            double total = order.getTotal();

            orderDataList.add(new OrderData(orderID, customerID, customerName, date, discount, total));
        }

        return orderDataList;
    }

    public void orderListSelect() {
        OrderData order = orderTableView.getSelectionModel().getSelectedItem();
        int num = orderTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        adminRentList = FXCollections.observableArrayList();
        adminRentList.removeAll();
        adminRentList = createRentalShowListData(order);


        orderColRentId.setCellValueFactory(new PropertyValueFactory<>("rentId"));
        orderColRentTitle.setCellValueFactory(new PropertyValueFactory<>("rentTitle"));
        orderColRentItemType.setCellValueFactory(new PropertyValueFactory<>("rentItemType"));
        orderColRentQty.setCellValueFactory(new PropertyValueFactory<>("rentQty"));
        orderColRentFee.setCellValueFactory(new PropertyValueFactory<>("rentFee"));
        orderColRentDueDate.setCellValueFactory(new PropertyValueFactory<>("rentDueDate"));
        orderColRentStatus.setCellValueFactory(new PropertyValueFactory<>("rentStatus"));


        orderRentTableView.setItems(adminRentList);
        orderRentTableView.refresh();

    }

    public static ObservableList<RentalData> createRentalShowListData(OrderData orderData) {
        ObservableList<RentalData> rentalDataList = FXCollections.observableArrayList();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (Order order : orderList) {
            if (order.getOrderID().equals(orderData.getOrderID())) {
                for (Item item : order.getOrder().keySet()) {
                    OrderDetails orderDetails = order.getOrder().get(item);

                    String orderId = orderData.getOrderID();
                    String rentId = item.getId();
                    String rentTitle = item.getTitle();
                    ItemType rentItemType = item.getItemType();
                    int rentQty = orderDetails.getAmount();
                    double rentFee = item.getRentalFee() * rentQty - orderDetails.getDiscount();
                    String rentDueDate = dateFormat.format(orderDetails.getReturnDate());

                    String rentStatus = null;
                    Date today = new Date();
                    if (orderDetails.isReturned() == true) {
                        rentStatus = "Returned";
                    } else {
                        if (today.after(orderDetails.getReturnDate())) {
                            rentStatus = "Late";
                        } else {
                            rentStatus = "Not yet returned";
                        }
                    }

                    rentalDataList.add(new RentalData(orderId, rentId, rentTitle, rentItemType, rentQty, rentFee, rentDueDate, rentStatus));
                }

                break;
            }
        }

        return rentalDataList;
    }

    public void rentalListSelect() {
        RentalData rentalData = orderRentTableView.getSelectionModel().getSelectedItem();
        int num = orderRentTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        orderOrderTextField.setText(rentalData.getOrderId());
        orderItemIDTextField.setText(rentalData.getRentId());
        orderTitleTextField.setText(rentalData.getRentTitle());

        if (rentalData.getRentStatus().equals("Returned")) {
            returnBtn.setDisable(true);
            returnBtn.setText("Returned Successfully");
        } else {
            returnBtn.setDisable(false);
            returnBtn.setText("Return Item");
        }

    }


    /* View Settings */
    private double x;
    private double y;

    public void logout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("/com/no1/geniestore/homepage-view.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) ->  {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

                // write from Observable List to XML
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == ordersBtn) {
            addOrderForm.setVisible(true);
            addItemForm.setVisible(false);
            addCustomerForm.setVisible(false);

            ordersBtn.setStyle("-fx-background-color:#1e4622;-fx-text-fill:#fff");
            itemsBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
            customersBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
        } else if (event.getSource() == itemsBtn) {
            addOrderForm.setVisible(false);
            addItemForm.setVisible(true);
            addCustomerForm.setVisible(false);

            itemsBtn.setStyle("-fx-background-color:#1e4622;-fx-text-fill:#fff");
            ordersBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
            customersBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
        } else if (event.getSource() == customersBtn) {
            addOrderForm.setVisible(false);
            addItemForm.setVisible(false);
            addCustomerForm.setVisible(true);

            customersBtn.setStyle("-fx-background-color:#1e4622;-fx-text-fill:#fff");
            itemsBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
            ordersBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
        }
    }


//    protected String errorMessage = String.format("-fx-text-fill: RED;");
//    protected String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    /* Add Item Form */

        // Parse from XML to ObservableList of ItemData, can use thread to be faster
        try {
            addItemList = new ItemListParser().parse("xml/items.xml");
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        addItemShowListData();

        // Item ID
        itemId.setDisable(true);

        //Item Title
        itemTitleError.setVisible(false);
        itemTitle.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.isEmpty()) {
                    itemTitleError.setVisible(true);
                } else {
                    itemTitleError.setVisible(false);
                }
            }
        });

        // set remaining spinner disable
        remaining.setDisable(true);

        rentalFeeError.setVisible(false);
        yearComboBoxError.setVisible(false);
//        yearComboBox.getItems().addAll(yearComboList);
        for (int i = 1900; i <= 2023; i++) {
            yearComboBox.getItems().add(i);
        }
        yearComboBox.setEditable(true);

        SpinnerValueFactory<Integer> copiesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);

        copiesValueFactory.setValue(1);

        SpinnerValueFactory<Integer> remainingValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);

        remainingValueFactory.setValue(1);

        copies.setValueFactory(copiesValueFactory);
        remaining.setValueFactory(remainingValueFactory);

//        Get value from 2 spinners
//        itemId.setText(Integer.toString(copies.getValue()));

//        copies.valueProperty().addListener(new ChangeListener<Integer>() {
//            @Override
//            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
//                itemId.setText(Integer.toString(copies.getValue()));
//            }
//        });

        //    Conditions for rentalFee TextField
        rentalFee.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("(\\d+(\\.\\d*)?)?")) {
                    rentalFee.setText(oldValue);
                    rentalFeeError.setVisible(true);
                } else {
                    rentalFeeError.setVisible(false);
                }

//                try {
//                    Double.parseDouble(newValue);
//                    rentalFeeError.setVisible(false);
//                } catch (NumberFormatException exception) {
//                    rentalFee.setText(newValue.replaceAll("[\\D]", ""));
//                        rentalFeeError.setVisible(true);
//                }
            }
        });

        yearComboBox.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                int yearValue = 0;
                try {
                    yearValue = Integer.parseInt(newValue);
                } catch (NumberFormatException e) {}

                if(yearValue > 2023 || !newValue.matches("\\d*")) {
                    yearComboBox.getEditor().setText(oldValue);
                    yearComboBoxError.setVisible(true);
                } else {
                    yearComboBoxError.setVisible(false);
                }

            }
        });

        // Type content
        typeComboBox.getItems().addAll(ItemType.DVD, ItemType.GAME, ItemType.VIDEO_RECORD);

        // Genre content
        genreComboBox.getItems().addAll(Genre.ACTION, Genre.DRAMA, Genre.COMEDY, Genre.HORROR);

        // Loan type content
        loanTypeComboBox.getItems().addAll(LoanType.ONE_WEEK_LOAN, LoanType.TWO_DAY_LOAN);

    /* Add Account Form */
        addAccountShowListData();
        //    Conditions for addAccountReturnedItems TextField (only Integer)
        addAccountReturnedItems.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    addAccountReturnedItems.setText(newValue.replaceAll("[\\D]", ""));
                }
            }
        });

        //    Conditions for addAccountPoints TextField (only Integer)
        addAccountPoints.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    addAccountPoints.setText(newValue.replaceAll("[\\D]", ""));
                }
            }
        });

        // Account Level (Account Type) content
        addAccountLevelComboBox.getItems().addAll("Guest", "Regular", "VIP");

        // Disable ID and username field
        addAccountID.setDisable(true);
        addAccountUsername.setDisable(true);

    /* Order Form */
        // show list of orders
        orderShowListData();

        orderOrderTextField.setEditable(false);
        orderItemIDTextField.setEditable(false);
        orderTitleTextField.setEditable(false);
        returnBtn.setDisable(true);
        returnBtn.setText("Return Item");

    }
}
