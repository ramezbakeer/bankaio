AOS.init({
  duration: 800,
  easing: "slide",
  once: true,
});

jQuery(document).ready(function ($) {
  "use strict";

  $(".loader").delay(1000).fadeOut("slow");
  $("#overlayer").delay(1000).fadeOut("slow");

  var siteMenuClone = function () {
    $(".js-clone-nav").each(function () {
      var $this = $(this);
      $this
        .clone()
        .attr("class", "site-nav-wrap")
        .appendTo(".site-mobile-menu-body");
    });

    setTimeout(function () {
      var counter = 0;
      $(".site-mobile-menu .has-children").each(function () {
        var $this = $(this);

        $this.prepend('<span class="arrow-collapse collapsed">');

        $this.find(".arrow-collapse").attr({
          "data-toggle": "collapse",
          "data-target": "#collapseItem" + counter,
        });

        $this.find("> ul").attr({
          class: "collapse",
          id: "collapseItem" + counter,
        });

        counter++;
      });
    }, 1000);

    $("body").on("click", ".arrow-collapse", function (e) {
      var $this = $(this);
      if ($this.closest("li").find(".collapse").hasClass("show")) {
        $this.removeClass("active");
      } else {
        $this.addClass("active");
      }
      e.preventDefault();
    });

    $(window).resize(function () {
      var $this = $(this),
        w = $this.width();

      if (w > 768) {
        if ($("body").hasClass("offcanvas-menu")) {
          $("body").removeClass("offcanvas-menu");
        }
      }
    });

    $("body").on("click", ".js-menu-toggle", function (e) {
      var $this = $(this);
      e.preventDefault();

      if ($("body").hasClass("offcanvas-menu")) {
        $("body").removeClass("offcanvas-menu");
        $this.removeClass("active");
      } else {
        $("body").addClass("offcanvas-menu");
        $this.addClass("active");
      }
    });

    // click outisde offcanvas
    $(document).mouseup(function (e) {
      var container = $(".site-mobile-menu");
      if (!container.is(e.target) && container.has(e.target).length === 0) {
        if ($("body").hasClass("offcanvas-menu")) {
          $("body").removeClass("offcanvas-menu");
        }
      }
    });
  };
  siteMenuClone();

  var sitePlusMinus = function () {
    $(".js-btn-minus").on("click", function (e) {
      e.preventDefault();
      if ($(this).closest(".input-group").find(".form-control").val() != 0) {
        $(this)
          .closest(".input-group")
          .find(".form-control")
          .val(
            parseInt(
              $(this).closest(".input-group").find(".form-control").val()
            ) - 1
          );
      } else {
        $(this).closest(".input-group").find(".form-control").val(parseInt(0));
      }
    });
    $(".js-btn-plus").on("click", function (e) {
      e.preventDefault();
      $(this)
        .closest(".input-group")
        .find(".form-control")
        .val(
          parseInt(
            $(this).closest(".input-group").find(".form-control").val()
          ) + 1
        );
    });
  };
  // sitePlusMinus();

  var siteSliderRange = function () {
    $("#slider-range").slider({
      range: true,
      min: 0,
      max: 500,
      values: [75, 300],
      slide: function (event, ui) {
        $("#amount").val("$" + ui.values[0] + " - $" + ui.values[1]);
      },
    });
    $("#amount").val(
      "$" +
        $("#slider-range").slider("values", 0) +
        " - $" +
        $("#slider-range").slider("values", 1)
    );
  };
  // siteSliderRange();

  var siteCarousel = function () {
    if ($(".nonloop-block-13").length > 0) {
      $(".nonloop-block-13").owlCarousel({
        center: false,
        items: 1,
        loop: true,
        stagePadding: 0,
        margin: 0,
        autoplay: true,
        nav: true,
        navText: [
          '<span class="icon-arrow_back">',
          '<span class="icon-arrow_forward">',
        ],
        responsive: {
          600: {
            margin: 0,
            nav: true,
            items: 2,
          },
          1000: {
            margin: 0,
            stagePadding: 0,
            nav: true,
            items: 3,
          },
          1200: {
            margin: 0,
            stagePadding: 0,
            nav: true,
            items: 4,
          },
        },
      });
    }

    $(".single-text").owlCarousel({
      center: false,
      items: 1,
      loop: true,
      stagePadding: 0,
      margin: 0,
      autoplay: true,
      pauseOnHover: false,
      nav: false,
      smartSpeed: 1000,
    });
    $(".slide-one-item").owlCarousel({
      center: false,
      items: 1,
      loop: true,
      stagePadding: 0,
      margin: 0,
      autoplay: true,
      smartSpeed: 1000,
      pauseOnHover: false,
      nav: true,
      navText: [
        '<span class="icon-keyboard_arrow_left">',
        '<span class="icon-keyboard_arrow_right">',
      ],
    });

    $(".slide-one-item-alt").owlCarousel({
      center: false,
      items: 1,
      loop: true,
      stagePadding: 0,
      margin: 0,
      smartSpeed: 1000,
      autoplay: true,
      pauseOnHover: true,
      mouseDrag: false,
      touchDrag: false,
    });
    $(".slide-one-item-alt-text").owlCarousel({
      center: false,
      items: 1,
      loop: true,
      stagePadding: 0,
      margin: 0,
      smartSpeed: 1000,
      autoplay: true,
      pauseOnHover: true,
      mouseDrag: false,
      touchDrag: false,
    });

    $(".custom-next").click(function (e) {
      e.preventDefault();
      $(".slide-one-item-alt").trigger("next.owl.carousel");
      $(".slide-one-item-alt-text").trigger("next.owl.carousel");
    });
    $(".custom-prev").click(function (e) {
      e.preventDefault();
      $(".slide-one-item-alt").trigger("prev.owl.carousel");
      $(".slide-one-item-alt-text").trigger("prev.owl.carousel");
    });
  };
  siteCarousel();

  var siteStellar = function () {
    $(window).stellar({
      responsive: false,
      parallaxBackgrounds: true,
      parallaxElements: true,
      horizontalScrolling: false,
      hideDistantElements: false,
      scrollProperty: "scroll",
    });
  };
  // siteStellar();

  var siteCountDown = function () {
    $("#date-countdown").countdown("2020/10/10", function (event) {
      var $this = $(this).html(
        event.strftime(
          "" +
            '<span class="countdown-block"><span class="label">%w</span> weeks </span>' +
            '<span class="countdown-block"><span class="label">%d</span> days </span>' +
            '<span class="countdown-block"><span class="label">%H</span> hr </span>' +
            '<span class="countdown-block"><span class="label">%M</span> min </span>' +
            '<span class="countdown-block"><span class="label">%S</span> sec</span>'
        )
      );
    });
  };
  siteCountDown();

  var siteDatePicker = function () {
    if ($(".datepicker").length > 0) {
      $(".datepicker").datepicker();
    }
  };
  siteDatePicker();

  var siteSticky = function () {
    $(".js-sticky-header").sticky({ topSpacing: 0 });
  };
  siteSticky();

  // navigation
  var OnePageNavigation = function () {
    var navToggler = $(".site-menu-toggle");
    $("body").on(
      "click",
      ".main-menu li a[href^='#'], .smoothscroll[href^='#'], .site-mobile-menu .site-nav-wrap li a",
      function (e) {
        e.preventDefault();

        var hash = this.hash;

        $("html, body").animate(
          {
            scrollTop: $(hash).offset().top,
          },
          600,
          "easeInOutExpo",
          function () {
            window.location.hash = hash;
          }
        );
      }
    );
  };
  OnePageNavigation();

  var siteScroll = function () {
    $(window).scroll(function () {
      var st = $(this).scrollTop();

      if (st > 100) {
        $(".js-sticky-header").addClass("shrink");
      } else {
        $(".js-sticky-header").removeClass("shrink");
      }
    });
  };
  siteScroll();

  var siteIstotope = function () {
    /* activate jquery isotope */
    var $container = $("#posts").isotope({
      itemSelector: ".item",
      isFitWidth: true,
    });

    $(window).resize(function () {
      $container.isotope({
        columnWidth: ".col-sm-3",
      });
    });

    $container.isotope({ filter: "*" });

    // filter items on button click
    $("#filters").on("click", "button", function () {
      var filterValue = $(this).attr("data-filter");
      $container.isotope({ filter: filterValue });
      $("#filters button").removeClass("active");
      $(this).addClass("active");
    });
  };

  siteIstotope();

  $(".fancybox").on("click", function () {
    var visibleLinks = $(".fancybox");

    $.fancybox.open(visibleLinks, {}, visibleLinks.index(this));

    return false;
  });
});
// Transaction page
document.querySelector(".export-btn").addEventListener("click", function () {
  // Logic to export the transaction table data to PDF or CSV
  alert("Export functionality coming soon!");
});

function filterAll() {
  var table, tr;
  table = document.getElementById("transactionsTable");
  tr = table.getElementsByTagName("tr");

  // Show all rows
  for (var i = 0; i < tr.length; i++) {
    tr[i].style.display = "";
  }
}

function filterStatus(status) {
  applyFilters({ status: status });
}

function filterByAmount(min, max = Infinity) {
  applyFilters({ amountRange: [min, max] });
}

function filterByDate(date) {
  applyFilters({ date: date });
}

function applyFilters({ status = "", amountRange = [], date = "" } = {}) {
  var table, tr, tdStatus, tdAmount, tdDate;
  table = document.getElementById("transactionsTable");
  tr = table.getElementsByTagName("tr");

  for (var i = 0; i < tr.length; i++) {
    tdStatus = tr[i].getElementsByTagName("td")[5]; // Status column
    tdAmount = tr[i].getElementsByTagName("td")[3]; // Amount column
    tdDate = tr[i].getElementsByTagName("td")[0]; // Date column

    let showRow = true;

    // Apply status filter
    if (status && tdStatus && tdStatus.textContent.trim() !== status) {
      showRow = false;
    }

    // Apply amount range filter
    if (amountRange.length === 2 && tdAmount) {
      let amount = parseFloat(tdAmount.textContent.replace(/[^\d.-]/g, ""));
      if (amount < amountRange[0] || amount > amountRange[1]) {
        showRow = false;
      }
    }

    // Apply date filter
    if (date && tdDate) {
      let rowDate = new Date(tdDate.textContent.trim());
      let filterDate = new Date(date);
      if (rowDate.toDateString() !== filterDate.toDateString()) {
        showRow = false;
      }
    }

    // Show or hide the row
    tr[i].style.display = showRow ? "" : "none";
  }
}

function exportToCSV() {
  let table = document.getElementById("userTransactionDetails");
  let rows = Array.from(table.rows);

  let csvContent = "data:text/csv;charset=utf-8,";
  rows.forEach((row) => {
    let cols = Array.from(row.cells).map((cell) => cell.innerText);
    csvContent += cols.join(",") + "\n";
  });

  let encodedUri = encodeURI(csvContent);
  let link = document.createElement("a");
  link.setAttribute("href", encodedUri);
  link.setAttribute("download", "transactions.csv");
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
}

function exportToPDF() {
  const { jsPDF } = window.jspdf;
  const doc = new jsPDF();

  let table = document.getElementById("userTransactionDetails");
  let rows = Array.from(table.rows);

  let startY = 10; // Initial Y position
  rows.forEach((row, rowIndex) => {
    let cols = Array.from(row.cells).map((cell) => cell.innerText);
    let line = cols.join(" | ");

    doc.text(line, 10, startY + rowIndex * 10); // Adjust Y for each row
  });

  doc.save("transactions.pdf");
}
