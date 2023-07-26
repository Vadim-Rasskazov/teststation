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
}
