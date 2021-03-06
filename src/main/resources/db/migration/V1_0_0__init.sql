create schema if not exists clb;

CREATE TABLE clb.city_locations_ru
(
  geoname_id integer NOT NULL,
  locale_code text,
  continent_code text,
  continent_name text,
  country_iso_code text,
  country_name text,
  subdivision_1_iso_code text,
  subdivision_1_name text,
  subdivision_2_iso_code text,
  subdivision_2_name text,
  city_name text,
  metro_code text,
  time_zone text,
  is_in_european_union text,
  CONSTRAINT "geoname_id_ru_pkey" PRIMARY KEY ("geoname_id")
);

CREATE UNIQUE INDEX "geoname_id_ru_unique_idx"
  ON clb.city_locations_ru
  USING btree
  (geoname_id);


CREATE TABLE clb.city_locations_en
(
  geoname_id integer NOT NULL,
  locale_code text,
  continent_code text,
  continent_name text,
  country_iso_code text,
  country_name text,
  subdivision_1_iso_code text,
  subdivision_1_name text,
  subdivision_2_iso_code text,
  subdivision_2_name text,
  city_name text,
  metro_code text,
  time_zone text,
  is_in_european_union text,
  CONSTRAINT "geoname_id_en_pkey" PRIMARY KEY ("geoname_id")
);

CREATE UNIQUE INDEX "geoname_id_en_unique_idx"
  ON clb.city_locations_en
  USING btree
  (geoname_id);

