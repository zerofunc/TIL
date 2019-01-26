package effectivejava.chapter5.item30;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class item30 {
    public void print() {
        // 1:1 관계일 때 서비스 단에서 조인
        List<Country> countrys = new ArrayList<Country>();
        List<CapitalCity> capitalCities = new ArrayList<CapitalCity>();

        /*
        Key : CountryId
        Value : Capital
         */
        Map<Integer, CapitalCity> capitalCityMap = capitalCities.stream()
                .collect(Collectors.toMap(CapitalCity::getCountryId, Function.identity()));

        countrys.stream()
                .filter(c -> capitalCityMap.containsKey(c.getId()))
                .map(c -> {
                    CapitalCity capitalCity = capitalCityMap.get(c.getId());
                    CountryCapital countryCapital = new CountryCapital();
                    countryCapital.setCountryName(c.getName());
                    countryCapital.setCapitalName(capitalCity.getName());

                    return countryCapital;
                })
                .collect(Collectors.toList());
    }
}

class Country {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public Country setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }
}

class CapitalCity {
    private Integer id;
    private Integer CountryId;
    private String name;

    public Integer getId() {
        return id;
    }

    public CapitalCity setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getCountryId() {
        return CountryId;
    }

    public CapitalCity setCountryId(Integer countryId) {
        CountryId = countryId;
        return this;
    }

    public String getName() {
        return name;
    }

    public CapitalCity setName(String name) {
        this.name = name;
        return this;
    }
}

class CountryCapital {
    private String countryName;
    private String capitalName;

    public String getCountryName() {
        return countryName;
    }

    public CountryCapital setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public CountryCapital setCapitalName(String capitalName) {
        this.capitalName = capitalName;
        return this;
    }
}