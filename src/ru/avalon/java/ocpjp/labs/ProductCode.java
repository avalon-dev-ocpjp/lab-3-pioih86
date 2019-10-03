package ru.avalon.java.ocpjp.labs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Класс описывает представление о коде товара и отражает соответствующую
 * таблицу базы данных Sample (таблица PRODUCT_CODE).
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class ProductCode {
    /**
     * Код товара
     */
    private String code;
    /**
     * Кода скидки
     */
    private char discountCode;
    /**
     * Описание
     */
    private String description;
    /**
     * savepoint to control changes during one connection
     */
    private Savepoint savepoint;

    /**
     * Основной конструктор типа {@link ProductCode}
     *
     * @param code         код товара
     * @param discountCode код скидки
     * @param description  описание
     */
    public ProductCode(String code, char discountCode, String description) {
        this.code = code;
        this.discountCode = discountCode;
        this.description = description;
    }

    /**
     * Инициализирует объект значениями из переданного {@link ResultSet}
     *
     * @param set {@link ResultSet}, полученный в результате запроса,
     *            содержащего все поля таблицы PRODUCT_CODE базы данных Sample.
     */
    private ProductCode(ResultSet set) {
        /*
         * TODO #05 реализуйте конструктор класса ProductCode
         */
        try {
            this.code = set.getString("PROD_CODE");
            this.description = set.getString("DESCRIPTION");
            this.discountCode = (char) set.getCharacterStream("DISCOUNT_CODE").read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает запрос на выбор всех записей из таблицы PRODUCT_CODE
     * базы данных Sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getSelectQuery(Connection connection) throws SQLException {
        /*
         * TODO #09 Реализуйте метод getSelectQuery
         */
        ResourceManager resource = new ResourceManager();
        String query = resource.readSQL("select.sql");
        return connection.prepareStatement(query);
    }

    private static PreparedStatement getOneElemSelectQuery(Connection connection) throws SQLException {
        ResourceManager resourceManager = new ResourceManager();
        String query = resourceManager.readSQL("select_one.sql");
        return connection.prepareStatement(query);
    }

    /**
     * Возвращает запрос на добавление записи в таблицу PRODUCT_CODE
     * базы данных Sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getInsertQuery(Connection connection) throws SQLException {
        /*
         * TODO #10 Реализуйте метод getInsertQuery
         */
        ResourceManager resource = new ResourceManager();
        String query = resource.readSQL("insert.sql");
        return connection.prepareStatement(query);
    }

    /**
     * Возвращает запрос на обновление значений записи в таблице PRODUCT_CODE
     * базы данных Sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getUpdateQuery(Connection connection) throws SQLException {
        /*
         * TODO #11 Реализуйте метод getUpdateQuery
         */
        ResourceManager resource = new ResourceManager();
        String query = resource.readSQL("update.sql");
        return connection.prepareStatement(query);
    }

    /**
     * Преобразует {@link ResultSet} в коллекцию объектов типа {@link ProductCode}
     *
     * @param set {@link ResultSet}, полученный в результате запроса, содержащего
     *            все поля таблицы PRODUCT_CODE базы данных Sample
     * @return Коллекция объектов типа {@link ProductCode}
     * @throws SQLException
     */
    public static Collection<ProductCode> convert(ResultSet set) throws SQLException {
        /*
         * TODO #12 Реализуйте метод convert
         */
        List<ProductCode> productCodes = new ArrayList<>();
        while (set.next()) {
            productCodes.add(new ProductCode(set));
        }
        return productCodes;
    }

    /**
     * Возвращает все записи таблицы PRODUCT_CODE в виде коллекции объектов
     * типа {@link ProductCode}
     *
     * @param connection действительное соединение с базой данных
     * @return коллекция объектов типа {@link ProductCode}
     * @throws SQLException
     */
    public static Collection<ProductCode> all(Connection connection) throws SQLException {
        try (PreparedStatement statement = getSelectQuery(connection)) {
            try (ResultSet result = statement.executeQuery()) {
                return convert(result);
            }
        }
    }

    /**
     * Возвращает код товара
     *
     * @return Объект типа {@link String}
     */
    public String getCode() {
        return code;
    }

    /**
     * Устанавливает код товара
     *
     * @param code код товара
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Возвращает код скидки
     *
     * @return Объект типа {@link String}
     */
    public char getDiscountCode() {
        return discountCode;
    }

    /**
     * Устанавливает код скидки
     *
     * @param discountCode код скидки
     */
    public void setDiscountCode(char discountCode) {
        this.discountCode = discountCode;
    }

    /**
     * Возвращает описание
     *
     * @return Объект типа {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание
     *
     * @param description описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * makes discountCode String representation
     *
     * @return String discountCode
     */
    private String discountToString() {
        return Character.toString(discountCode);
    }

    /**
     * Хеш-функция типа {@link ProductCode}.
     *
     * @return Значение хеш-кода объекта типа {@link ProductCode}
     */
    @Override
    public int hashCode() {
        /*
         * TODO #06 Реализуйте метод hashCode
         */
        return Objects.hash(code, description, discountCode);
    }

    /**
     * Сравнивает некоторый произвольный объект с текущим объектом типа
     * {@link ProductCode}
     *
     * @param obj Объект, скоторым сравнивается текущий объект.
     * @return true, если объект obj тождественен текущему объекту. В обратном
     * случае - false.
     */
    @Override
    public boolean equals(Object obj) {
        /*
         * TODO #07 Реализуйте метод equals
         */
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ProductCode object = (ProductCode) obj;
        return getCode().equals(object.getCode()) && getDescription().equals(object.getDescription()) &&
                getDiscountCode() == object.getDiscountCode();
    }

    /**
     * Возвращает строковое представление кода товара.
     *
     * @return Объект типа {@link String}
     */
    @Override
    public String toString() {
        /*
         * TODO #08 Реализуйте метод toString
         */
        return getCode() + " " + getDiscountCode() + " " + getDescription();
    }

    /**
     * Сохраняет текущий объект в базе данных.
     * <p>
     * Если запись ещё не существует, то выполняется запрос типа INSERT.
     * <p>
     * Если запись уже существует в базе данных, то выполняется запрос типа UPDATE.
     *
     * @param connection действительное соединение с базой данных
     */
    public void save(Connection connection) throws SQLException {
        /*
         * TODO #13 Реализуйте метод convert
         */
        checkSavepiont(connection);
        //boolean showing if product already exist
        boolean foundProduct = false;
        //try to find current product in table by code
        try (PreparedStatement statement = getOneElemSelectQuery(connection)) {
            statement.setString(1, getCode());
            try (ResultSet selection = statement.executeQuery()) {
                //update if current product already exists or add new if it's not
                foundProduct = selection.next();
            }
        }
        if (foundProduct) {
            updateProduct(connection);
        } else {
            insertProduct(connection);
        }
    }

    /**
     * check savepoint to prevent multiple actions with current object
     * @param connection
     * @throws SQLException
     */
    private void checkSavepiont(Connection connection) throws SQLException {
        if (savepoint != null) {
            connection.rollback(savepoint);
        } else {
            savepoint = connection.setSavepoint();
        }
    }

    /**
     * update row with current product in table PROD_CODE
     * @param connection current db connection
     * @throws SQLException
     */
    private void updateProduct(Connection connection) throws SQLException {
        try (PreparedStatement updateStatement = getUpdateQuery(connection)) {
            updateStatement.setString(1, getDescription());
            updateStatement.setString(2, discountToString());
            updateStatement.setString(3, getCode());
            updateStatement.execute();
        }
    }

    /**
     * insert current product in table PRODUCT_CODE
     * @param connection current db connection
     * @throws SQLException
     */
    private void insertProduct(Connection connection) throws SQLException {
        try (PreparedStatement insertStatement = getInsertQuery(connection)) {
            insertStatement.setString(1, getCode());
            insertStatement.setString(2, getDescription());
            insertStatement.setString(3, discountToString());
            insertStatement.execute();
        }
    }
}
