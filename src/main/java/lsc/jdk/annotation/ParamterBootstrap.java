package lsc.jdk.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import lsc.clent.GgFrameworkClient;
import lsc.core.GgTable;
import lsc.core.IChangedService;
import lsc.core.MgrParameterQuery;
import lsc.core.MgrParametersResult;
import lsc.core.MgrParametersWord;
import lsc.jdk.annotation.service.ParameterService;
import lsc.util.GgFrameworkUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ParamterBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(ParamterBootstrap.class);

    private static boolean isInit = false;

    @Value("${dubbo.registry}")
    private String dubboRegistry;

    @Value("${listener.paramter.db}")
    private String listenerParamterDb;

    @Value("${listener.paramter.tb}")
    private String listenerParamterTb;

    @Autowired
    private ParameterService parameterService;

    @PostConstruct
    private void init() {
        logger.debug("init ParamterBootstrap config;dubbo_registry:[{}],listener_paramter_db:[{}],listener_paramter_tb:[{}]", dubboRegistry,
                listenerParamterDb, listenerParamterTb);
        if (!isInit) {
            reload();
        }
        ExecutorService pool = Executors.newFixedThreadPool(2);
        GgFrameworkUtil.initCuratorFramework(dubboRegistry);
        GgTable table = new GgTable();
        table.setDb(listenerParamterDb);
        table.setTable(listenerParamterTb);
        try {
            GgFrameworkClient.listener(table, new IChangedService() {
                @Override
                public void excute(GgTable table) {
                    reload();
                }
            }, pool);
        } catch (Exception e) {
            e.printStackTrace();
        }
        isInit = true;
    }

    /**
     * 〈重新加载参数〉 <br/>
     * 通过注解的方式更新Parameter
     */
    private void reload() {
        try {
            long b = System.currentTimeMillis();
            MySqlParameterTools.reload(CparameterFromMySql.class, getMySqlParamters(), new MySqlParameterCallback() {
                @Override
                public void call(String key, Object value, String info) {
                    parameterService.insertParameter(key, value, info);
                }
            });
            logger.debug("loadPortraitParameters,cost:[{}]", (System.currentTimeMillis() - b));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<MgrParametersWord> getMySqlParamters() {
        MgrParameterQuery query = new MgrParameterQuery();
        query.setParameterKeys(reflexParamterKeys());
        MgrParametersResult result = parameterService.queryParameters(query);
        return result != null ? result.getList() : new ArrayList<MgrParametersWord>();
    }

    private List<String> reflexParamterKeys() {
        List<String> reflexKeys = new ArrayList<>();
        Class<CparameterFromMySql> paramterClass = CparameterFromMySql.class;
        Field[] fieldArray = paramterClass.getDeclaredFields();
        for (Field field : fieldArray) {
            MySqlParameterField annotation = field.getAnnotation(MySqlParameterField.class);
            reflexKeys.add(annotation.key());
        }
        logger.debug("reload reflexParamterKeys is:[{}]", reflexKeys);
        return reflexKeys;
    }
}
