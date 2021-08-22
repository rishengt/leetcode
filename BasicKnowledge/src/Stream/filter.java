package Stream;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class filter {
    public static void main(String[] args) {
//         List<List<List<Integer>>> listLayer3 = new ArrayList<>();
//         List<List<Integer>> listLayer2_1 = new ArrayList<>();
//         List<List<Integer>> listLayer2_2 = new ArrayList<>();
//         List<Integer> list1 = new ArrayList<>();
//         list1.add(1);
//         list1.add(2);
//         list1.add(3);
//         list1.add(4);
//        List<Integer> list2 = new ArrayList<>();
//        list2.add(1);
//        list2.add(2);
//        list2.add(3);
//        list2.add(4);
//        List<Integer> list3 = new ArrayList<>();
//        list3.add(1);
//        list3.add(2);
//        list3.add(3);
//        list3.add(4);
//        List<Integer> list4 = new ArrayList<>();
//        list4.add(1);
//        list4.add(2);
//        list4.add(3);
//        list4.add(4);
//        listLayer2_1.add(list1);
//        listLayer2_1.add(list2);
//        listLayer2_2.add(list3);
//        listLayer2_2.add(list4);
//        listLayer3.add(listLayer2_1);
//        listLayer3.add(listLayer2_2);
//        System.out.println(listLayer3.stream().flatMap(listLayer2->listLayer2.stream()).flatMap(listLayer1->listLayer1.stream()).filter(a->a==2).collect(Collectors.toList()));

//        System.out.println(lists);
        CoveragePartyByEci coveragePartyByEci = new CoveragePartyByEci();
        List<CoverageParty> getPartyBeEci = new ArrayList<>();
        CoverageParty coverageParty = new CoverageParty();
        List<GlobalClientCoverage> globalClientCoverages = new ArrayList<>();
        List<ClientTeamCoverage> clientTeamCoverages1 = new ArrayList<>();
        GlobalClientCoverage globalClientCoverage1 = new GlobalClientCoverage();
        globalClientCoverage1.setBookingBranchName("REL");
        ClientTeamCoverage clientTeamCoverage1_1 = new ClientTeamCoverage();
        clientTeamCoverage1_1.setTeam(new Team("CST"));
        clientTeamCoverage1_1.setTeamRoleIdentifier(1032);
        clientTeamCoverages1.add(clientTeamCoverage1_1);
        ClientTeamCoverage clientTeamCoverage1_2 = new ClientTeamCoverage();
        clientTeamCoverage1_2.setTeam(new Team("CST"));
        clientTeamCoverage1_2.setTeamRoleIdentifier(1030);
        clientTeamCoverages1.add(clientTeamCoverage1_2);
        globalClientCoverage1.setClientTeamCoverage(clientTeamCoverages1);
        globalClientCoverages.add(globalClientCoverage1);


        GlobalClientCoverage globalClientCoverage2 = new GlobalClientCoverage();
        globalClientCoverage2.setBookingBranchName("USA");
        List<ClientTeamCoverage> clientTeamCoverages2 = new ArrayList<>();
        ClientTeamCoverage clientTeamCoverage2_1 = new ClientTeamCoverage();
        globalClientCoverage2.setClientTeamCoverage(clientTeamCoverages2);
        globalClientCoverages.add(globalClientCoverage2);
        clientTeamCoverage2_1.setTeam(new Team("CST"));
        clientTeamCoverage2_1.setTeamRoleIdentifier(1032);
        clientTeamCoverages2.add(clientTeamCoverage2_1);
        ClientTeamCoverage clientTeamCoverage2_2 = new ClientTeamCoverage();
        clientTeamCoverage2_2.setTeam(new Team("CST"));
        clientTeamCoverage2_2.setTeamRoleIdentifier(1030);
        clientTeamCoverages2.add(clientTeamCoverage2_2);
        ClientTeamCoverage clientTeamCoverage2_3 = new ClientTeamCoverage();
        clientTeamCoverage2_3.setTeam(new Team("RRJ"));
        clientTeamCoverage2_3.setTeamRoleIdentifier(1034);
        clientTeamCoverages2.add(clientTeamCoverage2_3);
        coveragePartyByEci.setGetPartyByEci(getPartyBeEci);
        coverageParty.setGlobalClientCoverages(globalClientCoverages);
        getPartyBeEci.add(coverageParty);

//        String fk = "";
//        coveragePartyByEci.getGetPartyByEci().stream().flatMap(partyByEci ->
//                partyByEci.getGlobalClientCoverages().stream().filter(globalClientCoverage ->
//                        globalClientCoverage.getBookingBranchName().equalsIgnoreCase("REL"))).collect(Collectors.toList());
        List<CoverageParty> fk = coveragePartyByEci.getGetPartyByEci();
        List<GlobalClientCoverage> fker = fk.stream().flatMap(caverageParty->caverageParty.getGlobalClientCoverages().stream())
                .filter(clientTeamCoverage->clientTeamCoverage.getBookingBranchName().equalsIgnoreCase("REL"))
                .collect(Collectors.toList());
//        System.out.println(coveragePartyByEci.getGetPartyByEci().stream().flatMap(partyByEci->partyByEci.getGlobalClientCoverages().stream().filter(globalClientCoverage -> globalClientCoverage.getBookingBranchName().equalsIgnoreCase("REL"))).collect(Collectors.toList()));
;
        fker.get(0).getClientTeamCoverage().forEach(a->System.out.println(a.getTeamRoleIdentifier()));
        List<ClientTeamCoverage>faker = fker.stream().flatMap(globalClientCoverage -> globalClientCoverage.getClientTeamCoverage().stream()).filter(clientTeamCoverage -> clientTeamCoverage.getTeam().getTeamTypeCode().equalsIgnoreCase("CST"))
                .collect(Collectors.toList());

        new filter().filterTest();
    }

    public void filterTest(){
        List<Integer> list = Arrays.asList(new Integer[]{1,2,3,4});
        List<Integer> list1 = Arrays.asList(new Integer[]{1,2,3,4,5,6,7});
        list1.stream().filter(a->list.contains(a)).collect(Collectors.toList()).forEach(a-> System.out.println(a));
    }
}

 class CoveragePartyByEci{
    private List<CoverageParty> getPartyByEci;

    public CoveragePartyByEci() {
    }

    public List<CoverageParty> getGetPartyByEci() {
        return getPartyByEci;
    }

    public void setGetPartyByEci(List<CoverageParty> getPartyByEci) {
        this.getPartyByEci = getPartyByEci;
    }
}

 class CoverageParty{
    private List<GlobalClientCoverage> globalClientCoverages;

    public CoverageParty() {
    }

    public List<GlobalClientCoverage> getGlobalClientCoverages() {
        return globalClientCoverages;
    }

    public void setGlobalClientCoverages(List<GlobalClientCoverage> globalClientCoverages) {
        this.globalClientCoverages = globalClientCoverages;
    }
}

 class GlobalClientCoverage{
    private List<ClientTeamCoverage> clientTeamCoverage;

    public String getBookingBranchName() {
        return bookingBranchName;
    }

    public void setBookingBranchName(String bookingBranchName) {
        this.bookingBranchName = bookingBranchName;
    }

    private String bookingBranchName;

    public GlobalClientCoverage() {
    }

    public List<ClientTeamCoverage> getClientTeamCoverage() {
        return clientTeamCoverage;
    }

    public void setClientTeamCoverage(List<ClientTeamCoverage> clientTeamCoverage) {
        this.clientTeamCoverage = clientTeamCoverage;
    }
}
  class  ClientTeamCoverage{
    private String teamCode;
    private String teamName;
    private int teamRoleIdentifier;
    private String teamRoleName;
    private Team team;

    public ClientTeamCoverage() {
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamRoleIdentifier() {
        return teamRoleIdentifier;
    }

    public void setTeamRoleIdentifier(int teamRoleIdentifier) {
        this.teamRoleIdentifier = teamRoleIdentifier;
    }

    public String getTeamRoleName() {
        return teamRoleName;
    }

    public void setTeamRoleName(String teamRoleName) {
        this.teamRoleName = teamRoleName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
  class Team{
    private String teamTypeCode;

    public Team() {
    }

    public Team(String teamTypeCode) {
        this.teamTypeCode = teamTypeCode;
    }

    public String getTeamTypeCode() {
        return teamTypeCode;
    }

    public void setTeamTypeCode(String teamTypeCode) {
        this.teamTypeCode = teamTypeCode;
    }
}
