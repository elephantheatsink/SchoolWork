import * as d3 from 'd3';
import * as d3geo from 'd3-geo'
// import * as d3geoprojection from 'd3-geo-projection';
import * as topojson from 'topojson-client';

var width = 500;
var height = 500;
var showPlaceholder = false;

var projection = interpolatedProjection(
    // d3.geo.orthographic()
    d3geo.geoOrthographic()
        .rotate([10, -10])
        .center([-10, 10])
        .scale(240)
        .translate([width / 2, height / 2]),

    // d3.geo.equirectangular()
    d3geo.geoEquirectangular()
        .scale(153)
        .translate([width / 2, height / 2])
);

var path = d3.geoPath(projection);

function interpolatedProjection(a, b) {
    // var projection = d3.geo.projection(raw)
    var projection = d3geo.geoProjection(raw).scale(1),
        center = projection.center,
        translate = projection.translate,
        α;

    function raw(λ, φ) {
        var pa = a([λ *= 180 / Math.PI, φ *= 180 / Math.PI]), pb = b([λ, φ]);
        return [(1 - α) * pa[0] + α * pb[0], (α - 1) * pa[1] - α * pb[1]];
    }

    projection.alpha = function (_) {
        if (!arguments.length) return α;
        α = +_;
        var ca = a.center(), cb = b.center(),
            ta = a.translate(), tb = b.translate();
        center([(1 - α) * ca[0] + α * cb[0], (1 - α) * ca[1] + α * cb[1]]);
        translate([(1 - α) * ta[0] + α * tb[0], (1 - α) * ta[1] + α * tb[1]]);
        return projection;
    };

    delete projection.scale;
    delete projection.translate;
    delete projection.center;
    return projection.alpha(0);
}

const draw = (props) => {
    if (showPlaceholder) {
        // Remove anything inside viz div
        d3.select('.viz > *').remove();

        // Create svg component
        d3.select('.viz').append('svg')
            .attr('height', height)
            .attr('width', width)
            .attr('id', 'svg-viz');

        // Draw bounding rectangle
        d3.select('#svg-viz').append("rect")
            .attr("x", 5)
            .attr("y", 5)
            .attr('width', width - 10)
            .attr('height', height - 10)
            .attr("stroke-width", "5")
            .attr("stroke", "red")
            .attr("fill", "white");

        // Draw diagonal line
        d3.select('#svg-viz').append("line")
            .attr("x1", 5)
            .attr("y1", 5)
            .attr("x2", width - 5)
            .attr("y2", height - 5)
            .attr("stroke-width", "5")
            .attr("stroke", "red");

        // Draw diagonal line
        d3.select('#svg-viz').append("line")
            .attr("x1", width - 5)
            .attr("y1", 5)
            .attr("x2", 5)
            .attr("y2", height - 5)
            .attr("stroke-width", "5")
            .attr("stroke", "red");

        // Add first line of text
        d3.select('#svg-viz').append("text")
            .attr("x", (width / 2) - 48)
            .attr("y", (height / 2) - 5)
            .attr("font-weight", "bold")
            .text("Visualization");

        // Add second line of text
        d3.select('#svg-viz').append("text")
            .attr("x", (width / 2) - 45)
            .attr("y", (height / 2) + 15)
            .attr("font-weight", "bold")
            .text("Placeholder");
    }
    else {
        // Remove anything inside viz div
        d3.select('.viz > *').remove();
        
        var svg = d3.selectAll('.viz').insert("svg", ":first-child")
            .attr("width", width)
            .attr("height", height);
        var m0, o0;

        // svg.insert("test")

        // var centroid = d3.geo.path()
        //     .projection(function (d) { return d; })
        //     .centroid;

        var centroid = d3geo.geoPath()
            .projection(function (d) { return d; });
        // d3geo.geoCentroid();

        // var path = d3.geo.path()
        //     .projection(projection);
        // var path = d3.geoPath(projection);

        // var graticule = d3.geo.graticule()
        var graticule = d3.geoGraticule()
            .extent([[-180, -90], [180 - .1, 90 - .1]]);

        d3.json("https://fireriskalliance.github.io/web/Mesh-0510.json").then(function(world) {
        // d3.json("https://fireriskalliance.github.io/web/Mesh-0510.json", (error, world) => {
            // d3.json("https://fireriskalliance.github.io/web/Mesh-0510.json", function (error, world) {
            if (world.err) {
                console.warn(world.err);
                return;
            }

            // var countries = topojson.object(world, world.objects.Mesh).geometries,
            var countries = topojson.feature(world, world.objects.Mesh).features;//,
                // i = -1,
                // n = countries.length;

            projection.clipAngle(180);

            var backCountry = svg.selectAll(".back-country")
                .data(countries)
                .join(
                    enter => enter.insert("path", ".back-line")
                                .attr("class", "back-country cell")
                                .attr("id", function (d) { return "c" + d.id; })
                                .attr("d", path),
                    update => update.attr("class", "back-country cell")
                                .attr("id", function (d) { return "c" + d.id; })
                                .attr("d", path)
                )
                // .enter().insert("path", ".back-line")
                // .attr("class", "back-country cell")
                // .attr("id", function (d) { return "c" + d.id; })
                // .attr("d", path);

            // Should be 90, but then the top half-circle border line shows
            projection.clipAngle(89.5);

            var country = svg.selectAll(".country")
                .data(countries)
                .join(
                    enter => enter.insert("path", ".line")
                                .attr("class", "country cell")
                                .attr("id", function (d) { return "c" + d.id; })
                                .attr("d", path),
                    update => update.attr("class", "country cell")
                                .attr("id", function (d) { return "c" + d.id; })
                                .attr("d", path)
                )
                // .enter().insert("path", ".line")
                // .attr("class", "country cell")
                // .attr("id", function (d) { return "c" + d.id; })
                // .attr("d", path)
                .on("mouseover", function (d) {

                    if (d === undefined) {
                    }
                    else {
                        // div.transition()
                        //     .duration(200)
                        //     .style("opacity", .9);
                        /*
                            if (ActiveDataType="DropSize") {
                                div	.html("<table id='tooltip'>" + "<tr><td class='c1'><div style='width: 60px;'>Location:</div></td><td class='c2'>" + d3.select(this).attr("c") + "</td></tr>" + "<tr><td>Raw DS50:</td><td>" + Round(d3.select(this).attr("ds"),2) + " " + CurrentDropSizeUnits + "</td></tr>" + "<tr><td>Scaled:</td><td>" + Round(d3.select(this).attr("ds_s"),2) + " " + CurrentDropSizeUnits + "</td></tr>" + "</table>")	    
                                .style("left", (d3.event.pageX-100) + "px")		
                                .style("top", (d3.event.pageY-100) + "px");	
                            }
                            else {
                                div	.html("<table id='tooltip'>" + "<tr><td class='c1'><div style='width: 60px;'>Location:</div></td><td class='c2'>" + d3.select(this).attr("c") + "</td></tr>" + "<tr><td>Raw Flux:</td><td>" + Round(d3.select(this).attr("v"),2) + " " + CurrentFluxUnits + "</td></tr>" + "<tr><td>Scaled:</td><td>" + Round(d3.select(this).attr("v_s"),2) + " " + CurrentFluxUnits + "</td></tr>" + "</table>")	    
                                .style("left", (d3.event.pageX-100) + "px")		
                                .style("top", (d3.event.pageY-100) + "px");	
                            }
                        */
                    }
                });

            var e90 = svg.append("path")
                .datum({ type: "LineString", coordinates: [[-180, 0], [-90, 0], [0, 0], [90, 0], [180, 0]] })
                .attr("class", "e90 red markers")
                .attr("d", path);

            //0º Degree Marker
            var r0 = svg.append("path")
                .datum({ type: "LineString", coordinates: [[0, 20], [0, 30]] })
                .attr("class", "r0 green markers")
                .attr("d", path);

            //90º Degree Marker
            var r1 = svg.append("path")
                .datum({ type: "LineString", coordinates: [[90, 20], [90, 30]] })
                .attr("class", "r0 cyan markers")
                .attr("d", path);

            //180º Degree Marker
            var r2 = svg.append("path")
                .datum({ type: "LineString", coordinates: [[180, 20], [180, 30]] })
                .attr("class", "r0 cyan markers")
                .attr("d", path);

            //270º Degree Marker
            var r3 = svg.append("path")
                .datum({ type: "LineString", coordinates: [[-90, 20], [-90, 30]] })
                .attr("class", "r0 cyan markers")
                .attr("d", path);


            refresh();
            // feature = svg.selectAll("path");


            // RotateReset();
        });
    }
}

function refresh() {
    d3.select("#svg-viz").selectAll(".country").attr("d", path);
    d3.select("#svg-viz").selectAll(".back-country").attr("d", path);
    d3.select("#svg-viz").selectAll(".line").attr("d", path);
    d3.select("#svg-viz").selectAll(".back-line").attr("d", path);
    d3.select("#svg-viz").selectAll(".markers").attr("d", path);
    // d3.select("#svg1").selectAll(".country").attr("d", path);
    // d3.select("#svg1").selectAll(".back-country").attr("d", path);
    // d3.select("#svg1").selectAll(".line").attr("d", path);
    // d3.select("#svg1").selectAll(".back-line").attr("d", path);
    // d3.select("#svg1").selectAll(".markers").attr("d", path);

    // d3.select("#svg2").selectAll(".country").attr("d", path);
    // d3.select("#svg2").selectAll(".back-country").attr("d", path);
    // d3.select("#svg2").selectAll(".line").attr("d", path);
    // d3.select("#svg2").selectAll(".back-line").attr("d", path);
    // d3.select("#svg2").selectAll(".markers").attr("d", path);    

    // d3.select("#svg3").selectAll(".country").attr("d", path);
    // d3.select("#svg3").selectAll(".back-country").attr("d", path);
    // d3.select("#svg3").selectAll(".line").attr("d", path);
    // d3.select("#svg3").selectAll(".back-line").attr("d", path);
    // d3.select("#svg3").selectAll(".markers").attr("d", path);
}

var rotate = d3_geo_greatArcInterpolator();
var d3_radians = Math.PI / 180;

function d3_geo_greatArcInterpolator() {
    var x0, y0, cy0, sy0, kx0, ky0,
    x1, y1, cy1, sy1, kx1, ky1,
    d,
    k;

    function interpolate(t) {
        var B = Math.sin(t *= d) * k,
            A = Math.sin(d - t) * k,
            x = A * kx0 + B * kx1,
            y = A * ky0 + B * ky1,
            z = A * sy0 + B * sy1;
        return [
        Math.atan2(y, x) / d3_radians,
        Math.atan2(z, Math.sqrt(x * x + y * y)) / d3_radians
        ];
    }

    interpolate.distance = function() {
        if (d == null) k = 1 / Math.sin(d = Math.acos(Math.max(-1, Math.min(1, sy0 * sy1 + cy0 * cy1 * Math.cos(x1 - x0)))));
        return d;
    };

    interpolate.source = function(_) {
        var cx0 = Math.cos(x0 = _[0] * d3_radians),
            sx0 = Math.sin(x0);
        cy0 = Math.cos(y0 = _[1] * d3_radians);
        sy0 = Math.sin(y0);
        kx0 = cy0 * cx0;
        ky0 = cy0 * sx0;
        d = null;
        return interpolate;
    };

    interpolate.target = function(_) {
        var cx1 = Math.cos(x1 = _[0] * d3_radians),
            sx1 = Math.sin(x1);
        cy1 = Math.cos(y1 = _[1] * d3_radians);
        sy1 = Math.sin(y1);
        kx1 = cy1 * cx1;
        ky1 = cy1 * sx1;
        d = null;
        return interpolate;
    };

    return interpolate;
}

export default draw
