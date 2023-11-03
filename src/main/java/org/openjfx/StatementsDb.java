package org.openjfx;

public class StatementsDb {
    String test = """
        SELECT t.id, t.name, p.version, t.description
        FROM test t JOIN project p
        ON t.project_id = p.id""";

    String result = """
        SELECT tr.id, t.name, tr.result, tr.time, tr.date, u.name user, r.version, tr.error_id number
        FROM test_result tr JOIN test t
        ON tr.test_id = t.id
        JOIN user u
        ON tr.user_id = u.id
        JOIN `release` r
        ON tr.release_id = r.id""";

    String log = """
        SELECT test_result_id, log
        FROM error_log""";

    String error = """
        SELECT e.id, e.name, el.grade, p.version, e.description
        FROM error e JOIN error_level el
        ON e.level_id = el.id
        JOIN project p
        ON el.project_id = p.id""";

    String stat = """
        SELECT period, total, error_count, most_often
        FROM test_stat""";

    String statCount = """
        SELECT COUNT(DISTINCT date)
        FROM test_result
        WHERE date NOT IN (SELECT period FROM test_stat) AND date!=CURDATE()""";

    String statInsert = """
        INSERT INTO test_stat (period, total, error_count, most_often)
        SELECT date period, COUNT(*) total, COUNT(error_id) error_count, (
            SELECT name
            FROM error JOIN test_result ON test_result.error_id=error.id
            WHERE test_result.date=(
                SELECT DISTINCT date
                FROM test_result
                WHERE date NOT IN (SELECT period FROM test_stat) AND date!=CURDATE()
                LIMIT 1)
            GROUP BY date, error_id
            ORDER BY COUNT(error_id) DESC
            LIMIT 1
            ) most_often
        FROM test_result
        WHERE date=(
            SELECT DISTINCT date
            FROM test_result
            WHERE date NOT IN (SELECT period FROM test_stat) AND date!=CURDATE()
             LIMIT 1)
        GROUP BY date""";
}
