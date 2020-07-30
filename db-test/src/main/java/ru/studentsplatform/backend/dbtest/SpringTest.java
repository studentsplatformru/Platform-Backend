package ru.studentsplatform.backend.dbtest;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;

import static org.dbunit.Assertion.assertEqualsIgnoreCols;

@Service
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SpringTest extends DataSourceBasedDBTestCase {

    private Logger LOGGER = LoggerFactory.getLogger(SpringTest.class);

    protected DataSource getDataSource() {

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(
                "jdbc:postgresql://studplatform-database.ctzczhzrzupg.eu-west-2.rds.amazonaws.com/testDB");
        dataSource.setUser("postgres");
        dataSource.setPassword("password256");
        return dataSource;
    }

    @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.REFRESH;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return getConnection().createDataSet();
    }

    /**
     * Очищает все записи во всех таблицах, затем заново наполняет их стандартными записями.
     */
    @After
    public void clean() throws Exception {
        tearDown();
        getConnection().getConnection().createStatement().executeUpdate(SpringTestConfig.getFillingString());
    }

    /**
     *  Тест использет эталонные данные из файла data.xml и сравнивает их с только что добавленным полем.
     * @throws Exception qwe
     */
    @Test
    public void anotherTest() throws Exception {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("data.xml")) {
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
            ITable expectedTable = expectedDataSet.getTable("task");
            Connection conn = getDataSource().getConnection();

            conn.createStatement()
                    .executeUpdate(
                            "INSERT INTO schedule_user_cell (id) VALUES (3);" +
                                    "INSERT INTO task (id, task_name, is_done, schedule_user_cell_id) VALUES (3, 'Math', false, 1);");
            ITable actualData = getConnection()
                    .createQueryTable(
                            "result_name",
                            "SELECT * FROM task WHERE task_name='Math'");

            assertEqualsIgnoreCols(expectedTable, actualData,
                    new String[]{"id", "created_by", "dead_line", "mark", "modified_by"});
        }
    }
}
