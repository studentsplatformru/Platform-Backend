package ru.studentsplatform.backend.dbtest;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;

import static org.dbunit.Assertion.assertEqualsIgnoreCols;

/**
 * Класс, использующий dbUnit для тестирования баз данных.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru)
 */
@Service
@RunWith(SpringRunner.class)
public class SpringTest extends DataSourceBasedDBTestCase {

    private Logger logger = LoggerFactory.getLogger(SpringTest.class);

    /**
     * Осуществляем подключение к тестовой БД.
     * @return Объект источника данных
     */
    protected DataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(
                "jdbc:postgresql://studplatform-database.ctzczhzrzupg.eu-west-2.rds.amazonaws.com/testDB");
        dataSource.setUser("postgres");
        dataSource.setPassword("password256");
        return dataSource;
    }

    /**
     * Опееделяем, какая функция будет срабатывать при вызове метода setUp().
     * @return Определяем, что при вызове метода будет обновляться подключение
     */
    @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.REFRESH;
    }

    /**
     * Опееделяем, какая функция будет срабатывать при вызове метода tearDown().
     * @return Определяем, что при вызове метода будут удалены все записи во всех таблицах
     */
    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }

    /**
     * Получакм данные из таблицы.
     * @return данные из таблицы
     * @throws Exception Если не удаётся достать данные из таблицы - будет проброшено исключение.
     */
    @Override
    protected IDataSet getDataSet() throws Exception {
        return getConnection().createDataSet();
    }

    /**
     * Создаёт таблицы в БД, если они отсутствуют.
     */
    @Before
    public void init() {
        try {
            getConnection().getConnection().createStatement().executeUpdate(
                    SpringTestConfig.getInitString());
            setUp();
        } catch (Exception e) {
            logger.error(() -> "test setup went really wrong!");
        }
    }

    /**
     * Очищает все записи во всех таблицах, затем заново наполняет их стандартными записями.
     */
    @After
    public void clean() {
        try {
            tearDown();
            getDataSource().getConnection().createStatement().executeUpdate(
                    SpringTestConfig.getFillingString());
        } catch (Exception e) {
            logger.error(() -> "test cleanUp went really wrong!");
            e.printStackTrace();
        }
    }

    /**
     *  Тест использет эталонные данные из файла data.xml и сравнивает их с только что добавленным полем,
     *  игнорируя колонки id, created_by, dead_line, mark и modified_by.
     */
    @Test
    public void taskTest() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("data.xml");
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
            ITable expectedTable = expectedDataSet.getTable("task");
            Connection conn = getDataSource().getConnection();

            conn.createStatement()
                    .executeUpdate(
                                    "INSERT INTO task (id, task_name, is_done, schedule_user_cell_id) " +
                                            "VALUES (4, 'Math', false, 1);");
            ITable actualData = getConnection()
                    .createQueryTable(
                            "result_name",
                            "SELECT * FROM task WHERE task_name='Math'");

            assertEqualsIgnoreCols(expectedTable, actualData,
                    new String[]{"id", "created_by", "dead_line", "mark", "modified_by"});
        } catch (Exception e) {
            logger.error(() -> "Error occurred while db testing!");
        }
    }
}
