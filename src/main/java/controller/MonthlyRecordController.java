package controller;

import model.Observer;
import model.Record;
import model.Subject;
import util.RecordState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonthlyRecordController implements Subject {
    private DataPresistenceController dataPresistenceController;
    private RecordController recordController;
    private List<Observer> listObservers;
    private List<Record> listRecords;

    public MonthlyRecordController(){
        this.dataPresistenceController = new DataPresistenceController();
        this.recordController = new RecordController(dataPresistenceController);
        this.listObservers = new ArrayList<>();
        this.listRecords = this.importDataFromCurrentMonth();
    }

    /**
     * Add a new record
     *
     * @param receitaDiaria
     * @param despesaFatura
     * @param despesa
     * @param IVA
     * @return boolean
     */
    public boolean addNewRecord(double receitaDiaria, double despesaFatura, double despesa, double IVA){
        Record record = this.recordController.createNewRecord(receitaDiaria, despesaFatura, despesa, IVA, LocalDate.now());

        if(record != null){
            this.listRecords.add(record);
            this.notifyObservers(); //notify all Observers
            return true;
        }

        return false;
    }

    /**
     * Edit current record
     *
     * @param receitaDiaria
     * @param despesaFatura
     * @param despesa
     * @param IVA
     * @return boolean
     */
    public boolean editRecord(double receitaDiaria, double despesaFatura, double despesa, double IVA){
        Record record = this.recordController.editRecord(receitaDiaria, despesaFatura, despesa, IVA, LocalDate.now());

        if(record != null){
            this.listRecords.set(this.listRecords.size()-1, record);
            this.notifyObservers(); //notify all Observers
            return true;
        }

        return false;
    }

    /**
     * Retrieve data from current month
     *
     * @return List of Record Objects
     */
    public List<Record> importDataFromCurrentMonth(){
        return dataPresistenceController.recordsInfoByMonth(LocalDate.now().getMonth().getValue());
    }

    /**
     * get record list
     * @return List of Record Objects
     */
    public List<Record> retrieveRecordList(){
        return this.listRecords;
    }

    /**
     * get total value of this month
     *
     * @return double
     */
    public double retreiveReceitaDiariaTotalValue(){
        if(this.listRecords.isEmpty()){
            return 0.0;
        }

        double sum = 0.0;

        for(Record rec : this.listRecords){
            sum += rec.getReceitaDiariaValor();
        }

        return sum;
    }

    /**
     * get total value of this month
     *
     * @return double
     */
    public double retreiveDespesaFaturaTotalValue(){
        if(this.listRecords.isEmpty()){
            return 0.0;
        }

        double sum = 0.0;

        for(Record rec : this.listRecords){
            sum += rec.getDespesaFaturaValor();
        }

        return sum;
    }

    /**
     * get total value of this month
     *
     * @return double
     */
    public double retreiveDespesaTotalValue(){
        if(this.listRecords.isEmpty()){
            return 0.0;
        }

        double sum = 0.0;

        for(Record rec : this.listRecords){
            sum += rec.getDespesaValor();
        }

        return sum;
    }

    /**
     * get total value of this month
     *
     * @return double
     */
    public double retreiveIVATotalValue(){
        if(this.listRecords.isEmpty()){
            return 0.0;
        }

        double sum = 0.0;

        for(Record rec : this.listRecords){
            sum += rec.getIVAValor();
        }

        return sum;
    }

    /**
     * Check if record already exists
     *
     * @return boolean
     */
    public boolean doesRecordExist(){

        if(this.listRecords.isEmpty()){
            return false;
        }

        Record lastRecord = this.listRecords.get(this.listRecords.size()-1);

        return lastRecord.getDate().isEqual(LocalDate.now());
    }

    /**
     * Save changes
     *
     * @return boolean
     */
    public boolean saveRecord(){
        Record newRecord = this.recordController.getNewRecord();

        if(newRecord == null){
            return false;
        }

        if(newRecord.getState() == RecordState.NEW){
            return this.recordController.save(false);
        }

        if(newRecord.getState() == RecordState.EDITED){
            return this.recordController.save(true);
        }

        return false;
    }

    @Override
    public void registerObserver(Observer o) {
        this.listObservers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int index = this.listObservers.indexOf(o);
        if(index >= 0){
            this.listObservers.remove(index);
        }
    }

    @Override
    public void notifyObservers() {
        for(Observer obs : this.listObservers){
            obs.update();
        }
    }
}
