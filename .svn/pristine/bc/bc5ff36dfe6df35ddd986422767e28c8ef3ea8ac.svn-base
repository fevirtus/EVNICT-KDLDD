/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanhanh.web.bean;

import dulieuthuthap.MHistorySanluongMix;
import dulieuthuthap.countThuThap;
import main.web.common.bean.BasePageCommon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Assets;
import main.web.common.bean.WorkingContext;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.AxesGridLines;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import vanhanh.remote.IVanHanhRemote;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class DuLieuThuThapBean extends BasePageCommon implements Serializable {

    //Du lieu lay cac group function la du lieu tuong minh cuoi cung
    private IVanHanhRemote iVanHanhRemote;
    private BarChartModel barModel;
    private TreeNode treeTable;
    private TreeNode nodeSelected;
    private String userId;
    private String orgIdSelected;
    private countThuThap counttt;
    private MHistorySanluongMix sanLuongMix;
    private int monthLoad;
    private int yearLoad;
    private List<S_Assets> listOrgByUserIdSorted = new ArrayList<>();
    private List<S_Assets> listOrgTreeTable = new ArrayList<>();
    private List<String> listLabelBarModel = new ArrayList<>();
    private List<S_Assets> listOrgSelected = new ArrayList<>();
    private List<MHistorySanluongMix> listSanLuongMix = new ArrayList<>();

    /**
     * Creates a new instance of DuLieuThuThapBean
     */
    public DuLieuThuThapBean() {

        userId = WorkingContext.getUserName();
        listOrgByUserIdSorted = getiVanHanhRemote().getOrgByUserIdSorted(userId);
        listOrgSelected = getiVanHanhRemote().getAllOrgTreetable(userId);
        if (listOrgByUserIdSorted.get(0).getOrgid().equals("NPT")) {
            int tong = 0;
            int thuthap = 0;
            for (S_Assets item : listOrgByUserIdSorted) {
                tong += getiVanHanhRemote().getCountTotalChiSoDD(monthLoad, yearLoad, item.getOrgid());
                thuthap += getiVanHanhRemote().getCountChiSoThuThapDD(monthLoad, yearLoad, item.getOrgid());
            }
            counttt = new countThuThap("NPT", tong, thuthap, tong - thuthap);
        } else {
            int tong = getiVanHanhRemote().getCountTotalChiSoDD(monthLoad, yearLoad, listOrgByUserIdSorted.get(0).getOrgid());
            int thuthap = getiVanHanhRemote().getCountChiSoThuThapDD(monthLoad, yearLoad, listOrgByUserIdSorted.get(0).getOrgid());
            counttt = new countThuThap(listOrgByUserIdSorted.get(0).getOrgid(), tong, thuthap, tong - thuthap);
        }

        createBarModel();
        treeTable = createTreeTable();
    }

    public void onSelectOrg() {
        try {
            if (nodeSelected != null) {
                S_Assets sanLuongSelected = (S_Assets) nodeSelected.getData();
                listSanLuongMix = getiVanHanhRemote().getSanLuongBySelect(monthLoad, yearLoad, sanLuongSelected.getOrgid(), userId);
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TreeNode createTreeTable() {
        try {
            TreeNode root0 = new CheckboxTreeNode("root", null);

            listOrgTreeTable = getiVanHanhRemote().getAllOrgTreetable(userId);
            List<S_Assets> listlv2 = listOrgTreeTable.stream().filter(item -> item.getOrlevel() == 2).collect(Collectors.toList());
            List<S_Assets> listlv3 = listOrgTreeTable.stream().filter(item -> item.getOrlevel() == 3).collect(Collectors.toList());

            if (listOrgTreeTable.stream().anyMatch(item -> item.getOrlevel() == 1)) {
                TreeNode root1 = new CheckboxTreeNode(listOrgTreeTable.stream().filter(item -> item.getOrlevel() == 1).findAny().orElse(null), root0);
                for (S_Assets item2 : listlv2) {
                    if ("NPT".equals(item2.getOrgidParent())) {
                        TreeNode root2 = new CheckboxTreeNode(item2, root1);
                        for (S_Assets item3 : listlv3) {
                            if (item3.getOrgidParent().equals(item2.getOrgid())) {
                                TreeNode root3 = new CheckboxTreeNode(item3, root2);
                            }
                        }
                    }
                }
                return root0;
            } else if (listOrgTreeTable.stream().anyMatch(item -> item.getOrlevel() == 2)) {
                for (S_Assets item2 : listlv2) {
                    TreeNode root2 = new CheckboxTreeNode(item2, root0);
                    for (S_Assets item3 : listlv3) {
                        if (item3.getOrgidParent().equals(item2.getOrgid())) {
                            TreeNode root3 = new CheckboxTreeNode(item3, root2);
                        }
                    }
                }
                return root0;
            } else {
                for (S_Assets itemx : listlv3) {
                    TreeNode root3 = new CheckboxTreeNode(itemx, root0);
                }
                return root0;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public void onLoadData() {
        try {
            createBarModel();
            if (orgIdSelected.equals("NPT")) {
                int tong = 0;
                int thuthap = 0;
                for (S_Assets item : listOrgByUserIdSorted) {
                    tong += getiVanHanhRemote().getCountTotalChiSoDD(monthLoad, yearLoad, item.getOrgid());
                    thuthap += getiVanHanhRemote().getCountChiSoThuThapDD(monthLoad, yearLoad, item.getOrgid());
                }
                counttt = new countThuThap("NPT", tong, thuthap, tong - thuthap);
            } else {
                int tong = getiVanHanhRemote().getCountTotalChiSoDD(monthLoad, yearLoad, listOrgByUserIdSorted.get(0).getOrgid());
                int thuthap = getiVanHanhRemote().getCountChiSoThuThapDD(monthLoad, yearLoad, listOrgByUserIdSorted.get(0).getOrgid());
                counttt = new countThuThap(listOrgByUserIdSorted.get(0).getOrgid(), tong, thuthap, tong - thuthap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getTongDiemDo() {
        int tong = 0;
        int thuthap = 0;
        for (S_Assets item : listOrgByUserIdSorted) {
            tong += getiVanHanhRemote().getCountTotalChiSoDD(monthLoad, yearLoad, item.getOrgid());
            thuthap += getiVanHanhRemote().getCountChiSoThuThapDD(monthLoad, yearLoad, item.getOrgid());
        }
        double result = (double) thuthap * 100 / tong;
        return result;
    }

    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.getLabel();

        int totalChiSoDD;
        int chiSoThuThapDD;

        List<Number> values = new ArrayList<>();
        for (S_Assets item : listOrgByUserIdSorted) {
            if (item.getOrgid().equals("NPT")) {
                values.add(getTongDiemDo());
            } else {
                totalChiSoDD = getiVanHanhRemote().getCountTotalChiSoDD(monthLoad, yearLoad, item.getOrgid());
                chiSoThuThapDD = getiVanHanhRemote().getCountChiSoThuThapDD(monthLoad, yearLoad, item.getOrgid());
                values.add((double) chiSoThuThapDD * 100 / totalChiSoDD);
            }
        }
        barDataSet.setData(values);

//        List<String> bgColor = new ArrayList<>();
//        bgColor.add("rgba(86, 167, 245,0.2)");
//        barDataSet.setBackgroundColor(bgColor);
//
//        List<String> borderColor = new ArrayList<>();
//        borderColor.add("rgb(86, 167, 245)");
//        barDataSet.setBorderColor(borderColor);
//        barDataSet.setBorderWidth(1);
        data.addChartDataSet(barDataSet);

        //set list label for chart
        listLabelBarModel.clear();
        for (S_Assets item : listOrgByUserIdSorted) {
            listLabelBarModel.add(item.getOrgcode());
        }
        data.setLabels(listLabelBarModel);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        AxesGridLines gridLines = new AxesGridLines();
        gridLines.setColor("rgba(126, 146, 185, .3)");
        linearAxes.setGridLines(gridLines);
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);

        CartesianLinearAxes xaxes = new CartesianLinearAxes();
        xaxes.setGridLines(gridLines);
        cScales.addXAxesData(xaxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Tỷ lệ thu thập số liệu");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#7e92b9");
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        barModel.setOptions(options);
    }

    public IVanHanhRemote
            getiVanHanhRemote() {
        try {
            if (iVanHanhRemote == null) {
                iVanHanhRemote = (IVanHanhRemote) EjbContext.getLocalEJBRemote(IVanHanhRemote.class
                        .getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
        } catch (Exception ex) {
            return null;
        }
        return iVanHanhRemote;
    }

    public String getOrgIdSelected() {
        return orgIdSelected;
    }

    public void setOrgIdSelected(String orgIdSelected) {
        this.orgIdSelected = orgIdSelected;
    }

    public MHistorySanluongMix getSanLuongMix() {
        return sanLuongMix;
    }

    public void setSanLuongMix(MHistorySanluongMix sanLuongMix) {
        this.sanLuongMix = sanLuongMix;
    }

    public List<MHistorySanluongMix> getListSanLuongMix() {
        return listSanLuongMix;
    }

    public void setListSanLuongMix(List<MHistorySanluongMix> listSanLuongMix) {
        this.listSanLuongMix = listSanLuongMix;
    }

    public TreeNode getNodeSelected() {
        return nodeSelected;
    }

    public void setNodeSelected(TreeNode nodeSelected) {
        this.nodeSelected = nodeSelected;
    }

    public TreeNode getTreeTable() {
        return treeTable;
    }

    public void setTreeTable(TreeNode treeTable) {
        this.treeTable = treeTable;
    }

    public List<S_Assets> getListOrgTreeTable() {
        return listOrgTreeTable;
    }

    public void setListOrgTreeTable(List<S_Assets> listOrgTreeTable) {
        this.listOrgTreeTable = listOrgTreeTable;
    }

    public countThuThap getCounttt() {
        return counttt;
    }

    public void setCounttt(countThuThap counttt) {
        this.counttt = counttt;
    }

    public int getMonthLoad() {
        return monthLoad;
    }

    public void setMonthLoad(int monthLoad) {
        this.monthLoad = monthLoad;
    }

    public int getYearLoad() {
        return yearLoad;
    }

    public List<S_Assets> getListOrgByUserIdSorted() {
        return listOrgByUserIdSorted;
    }

    public void setListOrgByUserIdSorted(List<S_Assets> listOrgByUserIdSorted) {
        this.listOrgByUserIdSorted = listOrgByUserIdSorted;
    }

    public List<S_Assets> getListOrgSelected() {
        return listOrgSelected;
    }

    public void setListOrgSelected(List<S_Assets> listOrgSelected) {
        this.listOrgSelected = listOrgSelected;
    }

    public void setYearLoad(int yearLoad) {
        this.yearLoad = yearLoad;
    }

    public List<S_Assets> getListOrgByUserId() {
        return listOrgByUserIdSorted;
    }

    public void setListOrgByUserId(List<S_Assets> listOrgByUserIdSorted) {
        this.listOrgByUserIdSorted = listOrgByUserIdSorted;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

}
