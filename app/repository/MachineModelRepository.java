package repository;

import models.MachineModel;

import io.ebean.EbeanServer;
import play.db.ebean.EbeanConfig;
import play.db.ebean.EbeanDynamicEvolutions;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

import static io.ebean.Ebean.getServer;
import static java.util.Optional.ofNullable;
import static java.util.concurrent.CompletableFuture.supplyAsync;


import java.util.List;

public class MachineModelRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public MachineModelRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext,
                             @SuppressWarnings("unused")
                                     EbeanDynamicEvolutions ebeanDynamicEvolutions /* Required for Ebean/DI bug */) {
        this.ebeanServer = getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
     }

    public List<MachineModel> findAll() {
        return ebeanServer.find(MachineModel.class).findList();
    }

    public MachineModel findById(Long id) {
        return ebeanServer.find(MachineModel.class).where().eq("id", id).findOne();
    }

    public void save(MachineModel machineModel) {
        machineModel.save();
    }

    public void delete(Long id) {
        ebeanServer.find(MachineModel.class).where().eq("id", id).delete();
    }
}