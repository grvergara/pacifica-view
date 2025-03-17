package repository;


import models.Machine;
import models.MachineModel;

import java.util.List;
import io.ebean.EbeanServer;
import models.Charity;
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


public class MachineRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public MachineRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext,
                             @SuppressWarnings("unused")
                                     EbeanDynamicEvolutions ebeanDynamicEvolutions /* Required for Ebean/DI bug */) {
        this.ebeanServer = getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
     }


    public List<Machine> findAll() {
        return ebeanServer.find(Machine.class).findList();
    }

    public Machine findById(Long id) {
        return ebeanServer.find(Machine.class).where().eq("id", id).findOne();
    }

    public List<Machine> findByMachineModel(MachineModel machineModel) {
        return ebeanServer.find(Machine.class).where().eq("machineModel", machineModel).findList();
    }

    public void save(Machine machine) {
        machine.save();
    }

    public void delete(Long id) {
        ebeanServer.find(Machine.class).where().eq("id", id).delete();
    }
}